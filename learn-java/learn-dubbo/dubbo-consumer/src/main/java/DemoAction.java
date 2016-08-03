import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mazhibin on 16/7/29
 */
public class DemoAction {

    private DemoServer server;

    public void setServer(DemoServer server) {
        this.server = server;
    }

    public void action(String name){
        System.out.println(server.sayHello(name));
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        DemoAction action = (DemoAction)ctx.getBean("demoAction");
        action.action("mzb");
    }
}
