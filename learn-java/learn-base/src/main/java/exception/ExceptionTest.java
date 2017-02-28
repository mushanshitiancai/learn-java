package exception;

/**
 * Created by mazhibin on 16/12/16
 */
public class ExceptionTest {

    public static void main(String[] args) {
        AInterface aInterface = new AInterfaceImpl();
        
        aInterface.test();
    }
}
