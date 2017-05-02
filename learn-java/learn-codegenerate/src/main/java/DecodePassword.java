import com.fxiaoke.common.PasswordUtil;

/**
 * Created by mazhibin on 17/4/1
 */
public class DecodePassword {
    public static void main(String[] args) {
        try {
            System.out.println( PasswordUtil.decode("F5DD05D90F0EBEFE3EC761091E055205C88857F9EAEA0A5F4408D18EDA9FACFF"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
