package fastjson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * Created by mazhibin on 17/4/7
 */
public class FastJsonTest {
    
    
    public static void main(String[] args) {
        A a = new A();
        a.setPauseLogin(true);

        B b = convert(a, B.class);

        System.out.println(b);
        System.out.println(JSON.toJSONString(b));
    }

    public static <T> T convert(Object input, Class<T> type) {
        String text = JSON.toJSONString(input);
        return JSON.parseObject(text, type);
    }
}
