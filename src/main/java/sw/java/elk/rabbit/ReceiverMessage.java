package sw.java.elk.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ReceiverMessage {
    Logger logger = LoggerFactory.getLogger(ReceiverMessage.class);
    @Autowired
    private IMessageService messageService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitListener(queuesToDeclare = @Queue( MQConstant.DEFAULT_QUEUE))
    public void process( @Payload byte[] content) {
        logger.info(new String(content));
        Object order = redisTemplate.opsForValue().get(content);
        if(order!=null) {
            System.out.println(order.toString());
            System.out.println("查询到订单，还在有效期，支付即成功");
        }else{
            System.out.println("没有查询到订单，无效支付");
        }
    }
}
