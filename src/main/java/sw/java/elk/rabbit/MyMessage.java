package sw.java.elk.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;

/**
 * @Author wenrenhao
 * @Date 2021-10-19 21:03
 * @Version 1.0
 */
public class MyMessage extends Message {
    public MyMessage(byte[] body, MessageProperties messageProperties) {
        super(body, messageProperties);
    }

}
