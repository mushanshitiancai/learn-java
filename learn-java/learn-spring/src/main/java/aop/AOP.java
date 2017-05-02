package aop;

import aop.a.A;
import aop.a.impl.AWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mazhibin on 16/9/26
 */
public class AOP {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:aop.xml");
        A a = ctx.getBean(AWrapper.class);
        System.out.println(a.play("mzb"));
    }
}
