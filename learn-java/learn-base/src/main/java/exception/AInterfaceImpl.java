package exception;

/**
 * Created by mazhibin on 16/12/16
 */
public class AInterfaceImpl implements AInterface {
    @Override
    public void test() {
        throw new RuntimeException();
    }
}
