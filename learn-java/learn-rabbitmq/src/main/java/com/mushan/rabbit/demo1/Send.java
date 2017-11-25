package com.mushan.rabbit.demo1;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by mazhibin on 16/8/8
 */
public class Send {
    public static final String HELLO_QUEUE = "hello";
    public static final String HI_QUEUE = "hi";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        //建立服务器连接,获取通道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("cl-dev-rabbitmq-ndss-m98rq6.docker.sdp");
        factory.setPort(6055);
        factory.setUsername("ndss_dev_nl9EAk");
        factory.setPassword("oqENVfQVqOsV");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明一个发送队列
        //声明一个队列是幂等的，仅仅在要声明的队列不存在时才创建
        channel.queueDeclare(HELLO_QUEUE, false, false, false, null);
        channel.queueDeclare(HI_QUEUE, false, false, false, null);

        for (int i = 0; i < 10; i++) {
            String msg = "Hello-";
            channel.basicPublish("", HELLO_QUEUE, null, msg.getBytes());
            System.out.println("Sent msg: " + msg);
        }

        for (int i = 0; i < 10; i++) {
            String msg = "Hi-";
            channel.basicPublish("", HI_QUEUE, null, msg.getBytes());
            System.out.println("Sent msg: " + msg);
        }

        //定义一个消费者
        QueueingConsumer helloConsumer = new QueueingConsumer(channel);
        channel.basicConsume(HELLO_QUEUE, false, helloConsumer);
        QueueingConsumer hiConsumer = new QueueingConsumer(channel);
        channel.basicConsume(HI_QUEUE, false, hiConsumer);

        //从队列中获取消息并消费
        while (true) {
            QueueingConsumer.Delivery delivery = helloConsumer.nextDelivery();
            String msg = new String(delivery.getBody());
            System.out.println("Received msg: " + msg);

            long deliveryTag = delivery.getEnvelope().getDeliveryTag();
            channel.basicAck(deliveryTag, false);

//            channel.basicCancel(helloConsumer.getConsumerTag());

            delivery = hiConsumer.nextDelivery();
            msg = new String(delivery.getBody());
            System.out.println("Received msg: " + msg);

            deliveryTag = delivery.getEnvelope().getDeliveryTag();
            channel.basicAck(deliveryTag, false);
        }

//        //关闭通道和连接
//        channel.close();
//        connection.close();
    }
}
