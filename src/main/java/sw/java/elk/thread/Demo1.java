package sw.java.elk.thread;


public class Demo1 {
    ThreadLocal id = new ThreadLocal();
    ThreadLocal name = new ThreadLocal();
    public Object getId(){
        return id.get();
    }
    public Object getName(){
        return name.get();
    }

    public void set(){
        id.set(Thread.currentThread().getId());
        name.set(Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException{
        Demo1 de = new Demo1();
        de.set();
        System.out.println(de.getId());
        System.out.println(de.getName());

        Thread th = new Thread(){
            @Override
            public void run(){
                de.set();
                System.out.println(de.getId());
                System.out.println(de.getName());
            }
        };
        th.start();
        th.join();
        System.out.println(de.getId());
        System.out.println(de.getName());
    }
}
