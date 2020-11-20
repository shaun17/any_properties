package sw.java.elk.thread.syn;

public class Synchro {
     Object o = new Object();
    public static void main(String[] args) {

        TH t1 = new TH();
        TH t2 = new TH();
        TH t3 = new TH();
        t1.start();
        t2.start();
        t3.start();

    }
}
