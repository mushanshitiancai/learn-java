package com.mushan.rabbit.spring.direct;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

/**
 * Created by mazhibin on 16/8/8
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("/direct.xml");

        //获取RabbitTemplate,用于发送消息
        RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);
        rabbitTemplate.convertAndSend(new HashMap<String,String>(){{put("a","1");}});

        System.out.println("end direct");

        Thread.sleep(1000);
        ctx.destroy();
    }
}
