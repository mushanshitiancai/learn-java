import com.mushan.rabbit.demo1.HelloServer;
import com.mushan.rabbit.demo1.FolderType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mazhibin on 16/7/29
 */
public class DemoAction {

    private DemoServer server;
    public HelloServer helloServer;

    public void setServer(DemoServer server) {
        this.server = server;
    }

    public void setHelloServer(HelloServer helloServer) {
        this.helloServer = helloServer;
    }

    public void action(String name){
        System.out.println(server.sayHello(name));
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        DemoAction action = (DemoAction)ctx.getBean("demoAction");
        action.action("mzb");

        FolderType num = action.helloServer.getNum(1);
        System.out.println(num);
    }
}
