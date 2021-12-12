package sw.java.elk.key;

public class E {
    public static void main(String[] args) {
        D d = new D();
        System.out.println(d);
        d(d);
        System.out.println(d);
    }
    public static void d(D d){
        d = new D();
        System.out.println(d);
    }
}
