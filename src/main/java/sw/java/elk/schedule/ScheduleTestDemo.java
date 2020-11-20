package sw.java.elk.schedule;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 计时器实现定时执行
 * 可设置在一段时间内执行，当条件符合时可终止
 */
public class ScheduleTestDemo {
    private static final Log log =  LogFactory.getLog(ScheduleTestDemo.class);
    public static void main(String[] args) throws Exception{

        long l = System.currentTimeMillis();
        LocalDateTime date = LocalDateTime.now();
        LocalDateTime localDateTime = date.plusSeconds(5);
        long until = LocalDateTime.now().until(localDateTime, ChronoUnit.SECONDS);
        Reference<AtomicInteger> re = new SoftReference<>(new AtomicInteger(0));
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        executorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println("mession complate！");
                re.get().incrementAndGet();
            }
        }, LocalDateTime.now().until(localDateTime, ChronoUnit.SECONDS),1, TimeUnit.SECONDS);
        while (true){

           if( re.get().get()==10){
               executorService.shutdown();
           }
        }
    }
}
