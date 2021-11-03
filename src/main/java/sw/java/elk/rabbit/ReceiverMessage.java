package sw.java.elk.rabbit;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ReceiverMessage {
    Logger logger = LoggerFactory.getLogger(ReceiverMessage.class);
    @Autowired
    private IMessageService messageService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queuesToDeclare = @Queue( MQConstant.DEFAULT_QUEUE))
    public void process( @Payload String content) {
        logger.info(new String(content));
        Object order = redisTemplate.opsForValue().get(content);
        if(order!=null) {
            System.out.println(order.toString());
            System.out.println("查询到订单，还在有效期，支付即成功");
        }else{
            System.out.println("没有查询到订单，无效支付");
        }
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(name =MQConstant.ORDER_QUEUE_NAME),//临时队列，队列名会随机
                    exchange = @Exchange(name = MQConstant.ORDER_EXCHANGE_NAME),
            arguments = {@Argument(name = "x-dead-letter-exchange", value = MQConstant.LETTER_EXCHANGE)
                    ,@Argument(name ="x-dead-letter-routing-key" ,value = MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME)})
    })
    public void orderProcess(Message message, Channel channel) throws IOException {
        logger.info(new String(message.getBody()));
        Object order = redisTemplate.opsForValue().get("order");
        if(order!=null) {
            System.out.println(order.toString());
            System.out.println("查询到订单，还在有效期，支付即成功");
            channel.basicNack(message.getMessageProperties().getDeliveryTag(),false,false);
        }else{
            System.out.println("没有查询到订单，无效支付");
        }
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(name =MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME ),//临时队列，队列名会随机
                    exchange = @Exchange(name = MQConstant.LETTER_EXCHANGE),
            key = MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME)
    })
    public void receiveA(Message message, Channel channel) throws IOException {
        System.out.println("收到死信消息A：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }
}
