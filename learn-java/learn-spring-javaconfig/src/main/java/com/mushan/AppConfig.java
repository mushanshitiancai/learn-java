package com.mushan;

import com.mushan.service.DemoService;
import com.mushan.service.impl.DemoServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by mazhibin on 17/3/10
 */
@Configuration
@ComponentScan("com.mushan")
public class AppConfig {
    
    AppConfig(){
        System.out.println("AppConfig start");
    }
    
    @Bean
    DemoService demoService(){
        System.out.println("init demoService");
        return new DemoServiceImpl();
    }
    
}
