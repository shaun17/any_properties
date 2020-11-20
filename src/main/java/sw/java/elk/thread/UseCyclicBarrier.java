package sw.java.elk.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class UseCyclicBarrier {
    static CyclicBarrier cb = new CyclicBarrier(5, new myThread());
    static List list = new ArrayList<>();
    static ThreadLocal tl = new ThreadLocal();

    public static void main(String[] args) {
        System.out.println("start");
        for (int i = 0; i < 5; i++) {
            new Th1().start();
        }
        System.out.println("end");
    }

    static class myThread extends Thread {
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
            for (int i=0;i<list.size();i++){
                System.out.println("---"+tl.get());
            }

        }
    }

    static class Th1 extends Thread {
        @Override
        public void run() {
            try {

                Random random = new Random();
                int i = random.nextInt(11);
                System.out.println(Thread.currentThread().getName()+"start sleep"+i);
                Thread.sleep(i*1000);
                list.add(Thread.currentThread().getName());
                cb.await();
                tl.set(Thread.currentThread().getName());

                System.out.println("都到齐了");

            } catch (Exception e) {

            }


        }
    }

}
