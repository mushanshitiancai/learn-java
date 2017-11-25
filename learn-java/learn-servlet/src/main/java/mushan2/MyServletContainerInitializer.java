package mushan2;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * Created by mazhibin on 2017/5/23 0023.
 */
public class MyServletContainerInitializer implements ServletContainerInitializer{

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {
        System.out.println(c);
        System.out.println(ctx);
    }
}
