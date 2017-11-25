package com.mushan.rabbit.spring.direct_manual_ack;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;

/**
 * Created by mazhibin on 16/8/8
 * http://blog.csdn.net/u010841296/article/details/52253684
 */
public class Consumer /*implements ChannelAwareMessageListener*/ {

//    private static String ackThread;
//
//    public void onMessage(Message message, Channel channel) throws Exception {
//        if (ackThread == null) {
//            ackThread = Thread.currentThread().getName();
//            System.out.println("ackThread = " + ackThread);
//        }
//
//        String body = Thread.currentThread().getName() + new String(message.getBody());
//        System.out.println(Thread.currentThread().getName() + " > " + body);
//        Thread.sleep(100);
//
//        if (ackThread.equals(Thread.currentThread().getName())) {
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//            System.out.println("ack " + Thread.currentThread().getName());
//        }else{
//            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
//        }
//    }


//    public void onMessage(Message message, Channel channel) throws Exception {
//
//
//        String body = Thread.currentThread().getName() + new String(message.getBody());
//        System.out.println(Thread.currentThread().getName() + " > " + body);
//
//        long deliveryTag = message.getMessageProperties().getDeliveryTag();
//
//        channel.basicNack(deliveryTag, false, true);
//
//
//        Thread.sleep(1000);
//    }


//    public void onMessage(Message message, Channel channel) throws Exception {
//        System.out.println(message.getMessageProperties());
//
//        System.out.println(new String(message.getBody()));
//    }

    public void run(Object arg){
        System.out.println(arg);
    }
}
