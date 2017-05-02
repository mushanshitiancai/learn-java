package basic;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mazhibin on 16/11/28
 */
public class BasicTest {

    public static void main(String[] args) {
        System.out.println(16 >>> 1);
        System.out.println(16 >> 1);
        
        System.out.println(-16 >>> 1);
        System.out.println(-16 >> 1);
        System.out.println(-16 << 1);
//        System.out.println(-16 <<< 1);

        Map<Integer,String> map = new HashMap<>();
        map.put(1,"hello");
    }
}
