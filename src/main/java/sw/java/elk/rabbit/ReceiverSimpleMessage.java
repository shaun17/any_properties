package sw.java.elk.rabbit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class ReceiverSimpleMessage {

    Logger logger  = LoggerFactory.getLogger(ReceiverSimpleMessage.class);

    @Autowired
    private IMessageService messageService;


    @RabbitListener(queuesToDeclare =@Queue(MQConstant.DEFAULT_EXCHANGE))
    public void process(String content) {
        logger.info(content);
    }

}
