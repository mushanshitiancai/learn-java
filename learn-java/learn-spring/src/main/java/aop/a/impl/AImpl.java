package aop.a.impl;

import aop.a.A;
import org.springframework.stereotype.Component;

/**
 * Created by mazhibin on 17/3/30
 */
@Component("AImpl")
public class AImpl implements A {
    @Override
    public String play(String name) {
        return "hello " + name;
    }
}
