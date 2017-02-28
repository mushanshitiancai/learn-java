import com.alibaba.fastjson.JSON;
import entity.People;
import org.junit.Test;

/**
 * Created by mazhibin on 16/11/14
 */
public class FastJonTest {

    @Test
    public void test(){
        People people = new People("mushan", 20);
        String json = JSON.toJSONString(people);
        System.out.println(json);

        String jsonStr = "{\"Name\":\"mushan\",\"Age\":20}";
        People people1 = JSON.parseObject(jsonStr, People.class);
        System.out.println(people1);
    }
}
