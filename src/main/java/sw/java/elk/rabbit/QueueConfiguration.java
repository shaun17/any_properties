package sw.java.elk.rabbit;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class QueueConfiguration {
    @Bean
    public DirectExchange defaultDirectExchange() {
        DirectExchange directExchange = new DirectExchange(MQConstant.DEFAULT_EXCHANGE, true, false);
        return directExchange;
    }

    @Bean
    public Queue defaultQueue() {
        return new Queue(MQConstant.DEFAULT_QUEUE, true);
    }

    @Bean
    public Binding defaultBinding() {
        return BindingBuilder.bind(defaultQueue()).to(defaultDirectExchange()).with(MQConstant.DEFAULT_ROUT);
    }

//
//    @Bean
//    public DirectExchange orderExchange() {
//        DirectExchange directExchange = new DirectExchange(MQConstant.ORDER_EXCHANGE_NAME, true, false);
//        return directExchange;
//    }
//
//    @Bean
//    public Queue orderQueue() {
//        Map map = new HashMap();
//        map.put("x-dead-letter-exchange", MQConstant.LETTER_EXCHANGE);
//        map.put("x-dead-letter-routing-key", MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
//        return QueueBuilder.durable(MQConstant.ORDER_QUEUE_NAME).withArguments(map).build();
//
//    }
//
//    @Bean
//    public Binding orderBinding() {
//        return BindingBuilder.bind(orderQueue()).to(orderExchange()).with(MQConstant.ORDER_ROUTING_NAME);
//    }
//
//    @Bean
//    public DirectExchange deadExchange() {
//        DirectExchange directExchange = new DirectExchange(MQConstant.LETTER_EXCHANGE, true, false);
//        return directExchange;
//    }
//
//    @Bean
//    public Queue deadLetterQueue() {
//        return new Queue(MQConstant.DEFAULT_DEAD_LETTER_QUEUE_NAME, true, false, false);
//    }
//
//    @Bean
//    public Binding deadLetterBinding() {
//        return BindingBuilder.bind(deadLetterQueue()).to(deadExchange()).with(MQConstant.DEFAULT_REPEAT_TRADE_QUEUE_NAME);
//    }

}
