import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by mazhibin on 16/9/8
 */
public class BeanUtilTest {
    @Test
    public void test() throws InvocationTargetException, NoSuchMethodException, InstantiationException,
            IllegalAccessException {
        A a = new A(10,true,true);
        B b = new B();
        BeanUtils.copyProperties(b,a);
//        BeanUtils.copyProperties(a,b);
        System.out.println(b);
    }
}

