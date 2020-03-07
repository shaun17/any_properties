package sw.java.elk.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = MQConstant.ORDER_QUEUE_NAME)
public class ReceiverMessage {
    @Autowired
    private IMessageService messageService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RabbitHandler
    public void process(String content) {
        Object order = redisTemplate.opsForValue().get(content);
        if(order!=null) {
            System.out.println(order.toString());
            System.out.println("查询到订单，还在有效期，支付即成功");
        }else{
            System.out.println("没有查询到订单，无效支付");

        }




    }
}
