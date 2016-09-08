import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * Created by mazhibin on 16/7/25
 */
public class Main {

    public static void main(String[] args) {
//        test1();
//        test2();
//        jdbcTest();
    }

    public static void jdbcTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("db.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) ctx.getBean("jdbcTemplate");
//        jdbcTemplate.update("INSERT INTO user (name) VALUES ('sb')");
        List<Map<String,Object>> ret = jdbcTemplate.queryForList("SELECT * FROM user");
        for(Map<String,Object> v:ret){
            System.out.println(v.get("id").getClass());
            System.out.println(v.get("name").getClass());
        }
//        System.out.println(ret);
    }

    public static void test2() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spel.xml");
        List<City> cityList = (List<City>) ctx.getBean("cityList");

        for (City city : cityList) {
            System.out.println(city);
//            System.out.println(city.getClass());
        }

        System.out.println();

        City result = (City) ctx.getBean("result");
        System.out.println(result);
    }

    public static void test1() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        Man man = (Man) ctx.getBean("man");
        man.speak();

        man = (Man) ctx.getBean("man2");
        man.speak();

        man = (Man) ctx.getBean("man3");
        man.speak();

        man = (Man) ctx.getBean("man4");
        man.speak();

//        man = (Man)ctx.getBean("man5");
//        man.speak();
        man = (Man) ctx.getBean("man55");
        man.speak();

        man = (Man) ctx.getBean("man6");
        man.speak();

        man = (Man) ctx.getBean("man7");
        man.speak();
        man.getSon().speak();
    }
}
