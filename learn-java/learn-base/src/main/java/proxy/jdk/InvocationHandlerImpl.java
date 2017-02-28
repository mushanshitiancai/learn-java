package proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by mazhibin on 17/1/6
 */
public class InvocationHandlerImpl implements InvocationHandler {
    
    Man man;

    public InvocationHandlerImpl(Man man) {
        this.man = man;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke arg=" + Arrays.toString(args));
        Object invoke = method.invoke(man, args);
        System.out.println("after invoke ret=" + invoke);
        return invoke;
    }
}
