package sw.java.elk.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class UseCyclicBarrier {
    static CyclicBarrier cb = new CyclicBarrier(5, new myThread());
    static List list = new ArrayList<>();

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
            for (int i=0;i<list.size();i++){
                System.out.println(list.get(i));
            }

        }
    }

    static class Th1 extends Thread {
        @Override
        public void run() {
            try {
                list.add(Thread.currentThread().getName());
                Random random = new Random();
                int i = random.nextInt(11);
                System.out.println("start sleep"+i);
                Thread.sleep(i*1000);
                cb.await();

            } catch (Exception e) {

            }


        }
    }

}
