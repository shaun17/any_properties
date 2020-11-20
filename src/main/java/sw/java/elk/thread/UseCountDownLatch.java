package sw.java.elk.thread;

import java.util.concurrent.CountDownLatch;

public class UseCountDownLatch{
    static CountDownLatch ct = new CountDownLatch(5);
    public static void main(String[] args) throws InterruptedException{
        System.out.println("start------->.....");
        System.out.println("t1 doing--------->");
        new Th1().start();
        System.out.println("t2 doing--------->");
        new Th2().start();

        System.out.println("t1 doing--------->");
        ct.await();
        System.out.println("end--------->");

    }
    static  class  Th1 extends Thread {
        @Override
        public void run(){
            for(int i =0;i<2;i++){
                System.out.println("cut1 -->"+i);
                ct.countDown();
                try{
                    Thread.currentThread().sleep(2000);
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        }
    }
    static class Th2 extends  Thread{
        @Override
        public void run(){
            for(int i =0;i<3;i++){
                System.out.println("cut2 -->"+i);
                ct.countDown();
            }
        }
    }
}
