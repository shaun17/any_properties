package sw.java.elk.queue;

import java.util.concurrent.DelayQueue;

public class DelayMain {
    public static void main(String[] args) throws InterruptedException {
        DelayTest1 t1 = new DelayTest1(System.currentTimeMillis()+1000,"111");
        DelayTest1 t2 = new DelayTest1(System.currentTimeMillis()+5000,"222");
        DelayTest1 t3 = new DelayTest1(System.currentTimeMillis()+10000,"333");
        DelayTest1 t4 = new DelayTest1(System.currentTimeMillis()+15000,"444");
        DelayTest1 t5 = new DelayTest1(System.currentTimeMillis()+3000,"555");
        DelayQueue<DelayTest1> queue = new DelayQueue<>();
        queue.put(t1);
        queue.put(t2);
        queue.put(t3);
        queue.put(t4);
        queue.put(t5);
        for (int i=0;i<5;i++){
            DelayTest1 take = queue.take();
            System.out.println(take.name);
        }

    }
}
