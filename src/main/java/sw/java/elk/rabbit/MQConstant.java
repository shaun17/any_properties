package sw.java.elk.rabbit;

public class MQConstant {
    private MQConstant(){}

    //exchange name
    public static final String DEFAULT_EXCHANGE = "exchange_name";

    //TTL QUEUE
    public static final String DEFAULT_DEAD_LETTER_QUEUE_NAME = "dead.letter.queue";

    //DLX repeat QUEUE 死信转发队列
    public static final String DEFAULT_REPEAT_TRADE_QUEUE_NAME = "repeat.trade.queue";

    //Hello 测试消息队列名称
    public static final String ORDER_QUEUE_NAME = "ORDER_TEST";
}
