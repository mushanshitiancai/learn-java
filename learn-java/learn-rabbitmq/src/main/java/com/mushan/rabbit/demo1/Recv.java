package com.mushan.rabbit.demo1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by mazhibin on 16/8/8
 */
public class Recv {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        //建立服务器连接,获取通道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("cl-dev-rabbitmq-ndss-m98rq6.docker.sdp");
        factory.setPort(6055);
        factory.setUsername("ndss_dev_nl9EAk");
        factory.setPassword("oqENVfQVqOsV");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println("Waiting for message...");

        //定义一个消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, false, consumer);

        //从队列中获取消息并消费
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("Received msg: " + msg);

//            long deliveryTag = delivery.getEnvelope().getDeliveryTag();
//            channel.basicAck(deliveryTag, false);
        }
    }
}
