package sw.java.elk.rabbit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.Map;

@Component
public class ReceiverSimpleMessage {

    Logger logger  = LoggerFactory.getLogger(ReceiverSimpleMessage.class);

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
                    exchange = @Exchange(name = "Fanout_exchange",type = "fanout") //绑定Exchange
            )
    })
    public void getExchangeMsg1(String msg){
        System.out.println("Fanout  1 接受消息 :"+msg);
    }


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//临时队列，队列名会随机
                    exchange = @Exchange(name = "Fanout_exchange",type = "fanout"))
    })
    public void getExchangeMsg2(String msg){
        System.out.println("Fanout  2 接受消息 :"+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//临时队列，队列名会随机
                    exchange = @Exchange(name = "Fanout_exchange",type = "fanout"))
    })
    public void getExchangeMsg3(String msg){
        System.out.println("Fanout  3 接受消息 :"+msg);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//临时队列，队列名会随机
                    exchange = @Exchange(name = "Routing_exchange",type = "direct"),
                    key = "error")
    })
    public void getRoutMsg4(String msg){
        System.out.println("error routting:"+msg);
    }
    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//临时队列，队列名会随机
                    exchange = @Exchange(name = "Routing_exchange",type = "direct"),
                    key =  {"error","logs","warning"})
    })
    public void getRoutMsg3(String msg){
        System.out.println("other routting:"+msg);
    }


  @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(name = "confirm_queue"),//临时队列，队列名会随机
                    exchange = @Exchange(name = "confirm_exchange",type = "direct"))
    })
    public void getUserCnfirm(Message msg, Channel channel) throws IOException {
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(),false);
        System.out.println("this is confirm msg:"+new String(msg.getBody()));
      channel.confirmSelect();
    }


    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,//临时队列，队列名会随机
                    exchange = @Exchange(name = "productTopic",type = "topic"),
                    key =  {"#.key.#"})
    })
    public void getTopMsg(String msg){
        System.out.println("topic mmmmm:"+msg);
    }
}

