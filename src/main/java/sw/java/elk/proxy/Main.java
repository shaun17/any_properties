package sw.java.elk.proxy;

public class Main
{
    public static void main(String[] args) {
        UserProxy userProxy = new UserProxy();
        UserI o =(UserI) userProxy.newProxyInstance(new User());
        o.method();
    }
}
