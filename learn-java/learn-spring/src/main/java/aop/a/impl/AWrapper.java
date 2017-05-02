package aop.a.impl;

import aop.a.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * Created by mazhibin on 17/3/30
 */
@Component("AWrapper")
//@Primary
public class AWrapper implements A {

//    @Autowired
//    @Qualifier("AImpl")
//    A a;

    @Override
    public String play(String name) {
        return "AWrapper " + name;
//        return "(" + a.play(name) + ")";
    }
}
