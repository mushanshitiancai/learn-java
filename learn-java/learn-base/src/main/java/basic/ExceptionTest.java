package basic;

/**
 * Created by mazhibin on 17/4/7
 */
public class ExceptionTest {

    public static void main(String[] args) {
        try {
            try {
                throw new IllegalArgumentException("a");
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                throw e;
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
