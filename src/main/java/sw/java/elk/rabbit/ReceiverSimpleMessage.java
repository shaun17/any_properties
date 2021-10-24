package sw.java.elk.rabbit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Map;

@Component
public class ReceiverSimpleMessage {

    Logger logger  = LoggerFactory.getLogger(ReceiverSimpleMessage.class);

    @Autowired
    private IMessageService messageService;

    @RabbitListener(queues = MQConstant.DEFAULT_QUEUE)
    public void process( @Payload byte[] content) {
        logger.info(new String(content));
    }


    @RabbitListener(queuesToDeclare = @Queue("workQueue"))
    public void getMsg1( @Payload byte[] msg){
        System.out.println("MSG1："+new String(msg));
    }

    @RabbitListener(queuesToDeclare = @Queue("workQueue"))
    public void getMsg2(String msg){
        System.out.println("MSG2："+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//临时队列，队列名会随机
                    exchange = @Exchange(name = "productFanout",type = "fanout") //绑定Exchange
            )
    })
    public void getExchangeMsg1(String msg){
        System.out.println("Fanout1消费开始:"+msg);
    }


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//临时队列，队列名会随机
                    exchange = @Exchange(name = "productFanout",type = "fanout"))
    })
    public void getExchangeMsg2(String msg){
        System.out.println("Fanout2:"+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//临时队列，队列名会随机
                    exchange = @Exchange("fanoutExchange"))
    })
    public void getExchangeMsg3(String msg){
        System.out.println("Fanout33333:"+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//临时队列，队列名会随机
                    exchange = @Exchange(name = "productRout",type = "direct"),
                    key = "error")
    })
    public void getRoutMsg4(String msg){
        System.out.println("rout1:"+msg);
    }
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//临时队列，队列名会随机
                    exchange = @Exchange(name = "productRout",type = "direct"),
                    key =  {"error","logs","warning"})
    })
    public void getRoutMsg3(String msg){
        System.out.println("rout222:"+msg);
    }

}

