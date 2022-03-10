package sw.java.elk.thread.syn;

import sw.java.elk.key.C;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

import static java.util.concurrent.Executors.*;

public class CycDemo {
    AtomicInteger t = new AtomicInteger(1);
    private static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        CycDemo demo = new CycDemo();
        Condition a = lock.newCondition();
        Condition b = lock.newCondition();
        Condition c = lock.newCondition();
        ExecutorService executorService = newFixedThreadPool(3);

        for (int i=0 ;i<20;i++){
            executorService.submit(demo.new Task("|",a,b,1));
            executorService.submit(demo.new Task("||",b,c,2));
            executorService.submit(demo.new Task("|||",c,a,0));
        }
        executorService.shutdown();
    }

    class Task implements Runnable {
        String out;
        int index;
        Condition cur;
        Condition next;
        Task(String out,Condition cur,Condition next,int index){
            this.out=out;
            this.cur=cur;
            this.next=next;
            this.index=index;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                if(t.get()%3!=index){
                    try {
                        cur.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(out);
                t.incrementAndGet();
                next.signal();
            }finally {
                lock.unlock();
            }

        }
    }


}