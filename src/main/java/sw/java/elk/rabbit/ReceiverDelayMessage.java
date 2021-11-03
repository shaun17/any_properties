package sw.java.elk.rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME)
public class ReceiverDelayMessage {
    @Autowired
    private IMessageService messageService;
    @Autowired
    private RedisTemplate  redisTemplate;

    @RabbitHandler
    public void process(String content) {

        Object order = redisTemplate.opsForValue().get(content);
        if(order!=null){
            System.out.println(order.toString());
            System.out.println("怎么可能查得到？！！！！");
        }else{
            messageService.print();

        }

    }


}
