package com.mushan.rabbit.demo1.learn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by mazhibin on 16/8/1
 */
public class Server {
    Logger logger = LoggerFactory.getLogger(Server.class);

    public int add(int a, int b) throws InterruptedException {
        logger.trace("param: {} {}",a,b);
        Thread.sleep(1*1000);
        logger.debug("param: {} {}",a,b);
        Thread.sleep(1*1000);
        logger.info("param: {} {}",a,b);
        Thread.sleep(1*1000);
        logger.warn("param: {} {}",a,b);
        Thread.sleep(1*1000);
        logger.error("param: {} {}",a,b);

        return a + b;
    }
}
