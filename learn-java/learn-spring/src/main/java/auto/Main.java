package auto;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mazhibin on 16/7/26
 */
public class Main {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("auto.xml");
        Soldier soldier = (Soldier) ctx.getBean("sb");
        soldier.attack();
    }
}
