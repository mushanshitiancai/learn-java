package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by mazhibin on 16/9/28
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Class<?> phone = Class.forName("reflect.Phone");
        System.out.println(phone.getName());

        Method call = phone.getMethod("call",Integer.TYPE);
        System.out.println(call);

        Class<?> staticMethods = Class.forName("reflect.StaticMethods");
//        Method[] methods = staticMethods.getMethods();
//        System.out.println(Arrays.toString(methods));
        Method run = staticMethods.getMethod("run", int.class);
        run.invoke(staticMethods,111);
    }
}
