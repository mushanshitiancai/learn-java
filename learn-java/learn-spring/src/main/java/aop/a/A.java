package aop.a;

import org.springframework.stereotype.Component;

/**
 * Created by mazhibin on 16/9/26
 */
@Component
public class A {
    public String play(String name) {
        System.out.println("A play: " + name);
        return "A";
    }
}
