package sw.java.elk.rabbit;

import java.io.Serializable;

public class SimpleMessage implements Serializable {
    private static final long serialVersionUID = 985013887184088188L;
    private String exchange;
    private String queueName;
    private String content;
    private long times;

    public SimpleMessage() {
        super();
    }

    public SimpleMessage(String queueName, String content, long times) {
        super();
        this.queueName = queueName;
        this.content = content;
        this.times = times;
    }

    public SimpleMessage(String exchange, String queueName, String content, long times) {
        super();
        this.exchange = exchange;
        this.queueName = queueName;
        this.content = content;
        this.times = times;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public long getTimes() {
        return times;
    }

    public void setTimes(long times) {
        this.times = times;
    }


}
