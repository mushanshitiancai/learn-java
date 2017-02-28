package com.mushan.learn.java.aop.service;

/**
 * Created by mazhibin on 16/12/30
 */
public abstract class BaseService {
    public String getServiceName(){
        return this.getClass().getName();
    }
}
