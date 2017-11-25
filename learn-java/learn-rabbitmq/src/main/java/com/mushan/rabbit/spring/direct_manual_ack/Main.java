package com.mushan.rabbit.spring.direct_manual_ack;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;

/**
 * Created by mazhibin on 16/8/8
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        AbstractApplicationContext ctx = new ClassPathXmlApplicationContext("/direct_manual_ack.xml");

        //获取RabbitTemplate,用于发送消息
        RabbitTemplate rabbitTemplate = ctx.getBean(RabbitTemplate.class);

//        for (int i = 0; i < 10; i++) {
            rabbitTemplate.convertAndSend(new Pojo("fuck"));
//        }

        System.out.println("end direct");

        Thread.sleep(100000);
        ctx.destroy();
    }
}
