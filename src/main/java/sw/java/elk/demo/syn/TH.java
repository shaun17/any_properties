package sw.java.elk.demo.syn;

public class TH extends Thread {
   static final Object o = new Object();
    @Override
    public void run() {
        synchronized (o) {
            System.out.println("synchronized");
            try {
                Thread.currentThread().sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}