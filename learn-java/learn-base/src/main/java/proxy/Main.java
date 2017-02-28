package proxy;

import proxy.jdk.Human;
import proxy.jdk.InvocationHandlerImpl;
import proxy.jdk.Man;

import java.lang.reflect.Proxy;

/**
 * Created by mazhibin on 17/1/5
 */
public class Main {
    public static void main(String[] args) {
        Human man = new Man();
        System.out.println(man.say());

        Object o = Proxy.newProxyInstance(man.getClass().getClassLoader(), man.getClass().getInterfaces(), new InvocationHandlerImpl((Man)man));
        System.out.println(((Human)o).say());
    }
}
