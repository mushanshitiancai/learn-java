import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;
import org.junit.Test;

import java.lang.reflect.Type;

/**
 * Created by mazhibin on 16/9/12
 */
public class Main {

    @Test
    public void test() {
        class MyExtraProcessor implements ExtraProcessor,ExtraTypeProvider {

            public void processExtra(Object object, String key, Object value) {
                System.out.println(key+"1");
            }

            public Type getExtraType(Object object, String key) {
                System.out.println(key+"2");
//                if(key.equals(Mapper.ID_KEY)){
//                    return ObjectId.class;
//                }
                return null;
            }
        }

        Man man = JSON.parseObject("{\"_age___\":\"1\",\"x\":1}", Man.class,new MyExtraProcessor());
        System.out.println(man);
    }
}
