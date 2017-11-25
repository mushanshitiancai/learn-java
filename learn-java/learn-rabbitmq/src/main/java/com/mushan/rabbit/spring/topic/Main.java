package com.mushan.rabbit.spring.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by mazhibin on 16/8/8
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("/topic.xml");

        //获取RabbitTemplate,用于发送消息
        RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);
        rabbitTemplate.convertAndSend("@Hello World@");

        System.out.println("end");

        Thread.sleep(1000);
        ctx.destroy();
    }
}
