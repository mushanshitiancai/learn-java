package com.mushan;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Administrator on 2017/5/16 0016.
 */
@Configuration
public class TestConfig {
    public TestConfig(){
        System.out.println("TestConfig start");
    }
}
