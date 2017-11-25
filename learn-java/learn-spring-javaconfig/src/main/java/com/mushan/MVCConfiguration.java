package com.mushan;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by Administrator on 2017/5/15 0015.
 */
@Configuration
@EnableWebMvc
public class MVCConfiguration extends WebMvcConfigurerAdapter {

    public MVCConfiguration(){
        System.out.println("MVCConfiguration start");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        System.out.println("addViewControllers");
        registry.addViewController("/login").setViewName("login");
    }
}