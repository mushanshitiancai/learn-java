import com.google.common.base.Strings;
import org.junit.Test;

/**
 * Created by mazhibin on 16/9/8
 */
public class StringTest {
    @Test
    public void stringTest(){
        System.out.println(Strings.padEnd("123456",5,'0'));
    }
}
