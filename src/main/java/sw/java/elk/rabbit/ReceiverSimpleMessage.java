package sw.java.elk.rabbit;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

}
