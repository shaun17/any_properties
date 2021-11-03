package sw.java.elk.rabbit;

public class MQConstant {
    private MQConstant(){}

    //exchange name
    public static final String DEFAULT_EXCHANGE = "my_default_exchange";

    //queue name
    public static final String DEFAULT_QUEUE = "default_queue";

    //routing name
    public static final String DEFAULT_ROUT = "default_routing";




    //TTL QUEUE
    public static final String DEFAULT_DEAD_LETTER_QUEUE_NAME = "dead.letter.queue";

    //DLX repeat QUEUE 死信转发队列
    public static final String DEFAULT_REPEAT_TRADE_QUEUE_NAME = "repeat.trade.queue";

    public static final String LETTER_EXCHANGE = "letter_exchange";

    //order消息队列名称
    public static final String ORDER_QUEUE_NAME = "order.queue";
    //order消息exchange名称
    public static final String ORDER_EXCHANGE_NAME = "order.exchange";
    //order消息exchange名称
    public static final String ORDER_ROUTING_NAME = "order.routing";


}
