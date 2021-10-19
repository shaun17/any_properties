package sw.java.elk.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sw.java.elk.rabbit.IMessageService;

/**
 * @Author wenrenhao
 * @Date 2021-10-19 20:55
 * @Version 1.0
 */
@RestController
@RequestMapping("/message")
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
    public void sendsimple(String mess){
        rabbitTemplate.convertAndSend(mess);
    }
    @GetMapping
    public String aa(String mess){
        return mess;
    }
}
