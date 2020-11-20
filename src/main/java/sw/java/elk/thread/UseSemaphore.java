package sw.java.elk.thread;

import java.util.concurrent.Semaphore;

public class UseSemaphore {
    public static void main(String[] args) {
        Ser s = new Ser();
        for (int i =0;i<5;i++){
            new MyThread(s).start();
        }

    }

    static class MyThread extends Thread{
        private Ser ser;
        MyThread(Ser a){
            super();
            ser = a;
        }

        @Override
        public void run(){
            ser.method();
        }
    }

    static class Ser {
        private Semaphore sp = new Semaphore(3);

        public void method() {
            try {
                sp.acquire();
                System.out.println(Thread.currentThread().getName() + ":doSomething start-" );

                Thread.currentThread().sleep(1000);
                sp.release();
            }catch (Exception e){

            }

        }
    }
}
