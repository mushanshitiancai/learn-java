package com.mushan.service.impl;

import com.mushan.service.DemoService;

/**
 * Created by mazhibin on 17/3/10
 */
public class DemoServiceImpl implements DemoService {
    public String hello(String name) {
        return "hello " + name;
    }
}
