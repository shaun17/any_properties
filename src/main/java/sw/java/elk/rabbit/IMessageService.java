package sw.java.elk.rabbit;

public interface IMessageService {
    /**
     * 发送消息到队列
     * @param exchangeName 交换机
     * @param message 消息内容
     */
    public void send(String exchangeName,String message);


    /**
     * 延迟发送消息到队列
     * @param exchangeName 交换机
     * @param message 消息内容
     * @param times 延迟时间 单位毫秒
     */
    public void sendDeadLetter(String exchangeName,String message,long times);

    public void print();

    }
