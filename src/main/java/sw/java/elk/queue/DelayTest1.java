package sw.java.elk.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class DelayTest1 implements Delayed {
    long time;
    String name;
    DelayTest1(long time,String name){
        this.time=time;
        this.name=name;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return time-System.currentTimeMillis();
    }

    @Override
    public int compareTo(Delayed o) {
        DelayTest1 dt = (DelayTest1) o;
        long diff = this.time-dt.time;
        if (diff <= 0) {// 改成>=会造成问题
            return -1;
        }else {
            return 1;
        }
    }
}
