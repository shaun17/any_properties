package sw.java.elk.proxy;

import java.lang.reflect.Proxy;

public class Main {
    public static void main(String[] args) {
        UserInvocationHandler userProxy = new UserInvocationHandler(new User());
        UserI user = (UserI)Proxy.newProxyInstance(UserI.class.getClassLoader(), new Class[]{UserI.class}, userProxy);
        user.method();
    }
}
