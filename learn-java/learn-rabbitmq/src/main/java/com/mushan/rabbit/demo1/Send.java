package com.mushan.rabbit.demo1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by mazhibin on 16/8/8
 */
public class Send {
    public static final String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {

        //建立服务器连接,获取通道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //声明一个发送队列
        //声明一个队列是幂等的，仅仅在要声明的队列不存在时才创建
        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        String msg = "Hello World!";
        channel.basicPublish("",QUEUE_NAME,null,msg.getBytes());
        System.out.println("Sent msg: "+msg);

        //关闭通道和连接
        channel.close();
        connection.close();
    }
}
