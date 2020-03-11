package sw.java.elk.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class UserProxy implements InvocationHandler {
    private Object c;
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(c,args);
    }
    public <T> Object newProxyInstance(Object clazz) {
        this.c=clazz;
        return Proxy.newProxyInstance(clazz.getClass().getClassLoader(),clazz.getClass().getInterfaces(),this);

    }
}
