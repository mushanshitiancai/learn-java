package com.mushan;

import com.mushan.service.DemoService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by mazhibin on 17/3/10
 */
public class AppBootstrap {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        DemoService demoService = ctx.getBean(DemoService.class);
        System.out.println(demoService.hello("fuck"));

        DemoService demoService2 = ctx.getBean(DemoService.class);
        System.out.println(demoService2.hello("fuck"));
    }
}
