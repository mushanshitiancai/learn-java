package com.mushan.learn;

/**
 * Created by mazhibin on 16/8/2
 */
public class Child extends Parent {
    public Class<?> hi(){
        logger.debug("fuck");
        return this.getClass();
    }
}
