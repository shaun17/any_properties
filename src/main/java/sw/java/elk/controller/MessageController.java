package sw.java.elk.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw.java.elk.rabbit.DLXMessage;
import sw.java.elk.rabbit.IMessageService;
import sw.java.elk.rabbit.MQConstant;
import sw.java.elk.rabbit.SimpleMessage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author wenrenhao
 * @Date 2021-10-19 20:55
 * @Version 1.0
 */
@RestController
public class MessageController {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    IMessageService iMessageService;

    /**
     * 使用默认的queue、exchange和channel
     * @return
     */
    @GetMapping("/sendsimple")
    public void sendsimple(){
        String messageId = String.valueOf(UUID.randomUUID());
        String messageData = "test message, hello!";
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Map<String,Object> map=new HashMap<>();
        map.put("messageId",messageId);
        map.put("messageData",messageData);
        map.put("createTime",createTime);
        SimpleMessage dlxMessage = new SimpleMessage();
        dlxMessage.setQueueName("queue");
        dlxMessage.setExchange("exchange");
        dlxMessage.setContent(messageData);

        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
//        rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE,MQConstant.DEFAULT_ROUT, message);
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        Message message = new Message(JSONObject.toJSONString(dlxMessage).getBytes(),messageProperties);
//        rabbitTemplate.convertAndSend(MQConstant.DEFAULT_EXCHANGE,MQConstant.DEFAULT_ROUT,message );
        rabbitTemplate.convertAndSend(MQConstant.DEFAULT_ROUT,message );
    }

    @GetMapping("/workQueue")
    public void workQueue(){
        for(int i=0;i<10;i++){
            rabbitTemplate.convertAndSend("workQueue","This is workQueue"+i);
        }

    }

    @GetMapping("/productFanout")
    public void productFanout(){
        for(int i=0;i<10;i++){
            rabbitTemplate.convertAndSend("Fanout_exchange","","This is Fanout "+i);
        }

    }

    @GetMapping("/productRout")
    public void productRout(){
        rabbitTemplate.convertAndSend("Routing_exchange","logs","logs Message");
        rabbitTemplate.convertAndSend("Routing_exchange","error","Error Message 1");
        rabbitTemplate.convertAndSend("Routing_exchange","error","Error Message 2");
        rabbitTemplate.convertAndSend("Routing_exchange","warning","warning Message");
    }

    @GetMapping("/UserConfirm")
    public void UserConfirm(){
        rabbitTemplate.convertAndSend("confirm_exchange","","回调测试接口1： 这是一条消息");
    }

}
