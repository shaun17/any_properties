package sw.java.elk.demo;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Exchanger;

public class UseExchange {
    static Exchanger ex = new Exchanger();
    public static void main(String[] args) {
        new Th1().start();
        new Th2().start();


    }
    static class Th1 extends  Thread{
        private String a = "this is th1";
        @Override
        public void run(){
            try {

                Object exchange = ex.exchange(a);
                System.out.println(exchange.toString()+Thread.currentThread().getName()+"-");

            }catch (Exception e){

            }


        }
    }
    static class Th2 extends  Thread{
        private String a = "this is th2";
        @Override
        public void run(){
            try {
                Object exchange = ex.exchange(a);
                System.out.println(exchange.toString()+Thread.currentThread().getName()+"+");
            }catch (Exception e){

            }

        }
    }
}
