package sw.java.elk.proxy.cglib;

import java.lang.annotation.Target;

public class Main {
    public static void main(String[] args) {
        CglibProxy c = new CglibProxy();
        CgUser target = (CgUser)c.newProxyInstance(CgUser.class);
        target.method();
    }
}
