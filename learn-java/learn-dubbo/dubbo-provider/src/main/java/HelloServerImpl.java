import com.mushan.rabbit.demo1.HelloServer;

/**
 * Created by mazhibin on 16/7/29
 */
public class HelloServerImpl implements HelloServer {
    public String getHello(String name) {
        return "mzb say hello to "+name;
    }
}
