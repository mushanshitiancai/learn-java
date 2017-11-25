package mushan;

import nd.Md5;
import test.AInterface;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.HandlesTypes;
import java.util.Set;

/**
 * Created by mazhibin on 2017/5/23 0023.
 */
@HandlesTypes({Md5.class,JarServlet.class, AInterface.class})
public class MyServletContainerInitializer implements ServletContainerInitializer{

    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println("learn-test/onStartup");
        System.out.println(c);
        System.out.println(ctx);
    }
}
