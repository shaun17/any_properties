package sw.java.elk.rabbit;

import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

@Service
public class IMessageServiceImpl implements IMessageService{
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Override
    public void send(String queueName, String message) {
        rabbitTemplate.convertAndSend(MQConstant.ORDER_EXCHANGE_NAME,queueName, message);
    }

    @Override
    public void sendDeadLetter(String queueName, String message, long times) {
        DLXMessage dlxMessage = new DLXMessage(MQConstant.DEFAULT_EXCHANGE,queueName,message,times);
        MessagePostProcessor processor = new MessagePostProcessor(){
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration(times + "");
                return message;
            }
        };
        rabbitTemplate.convertAndSend(MQConstant.ORDER_EXCHANGE_NAME,MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, JSONObject.toJSONString(dlxMessage), processor);

    }

    @Override
    public void print(){
        System.out.println("这里是规定时间没有处理，订单自动取消了");
    }
}
