//package com.meta.verse.rabbitMQ;
//
//import com.rabbitmq.client.Channel;
//import com.rabbitmq.client.Connection;
//import com.rabbitmq.client.ConnectionFactory;
//import com.rabbitmq.client.DeliverCallback;
//import org.springframework.stereotype.Service;
//
//import java.nio.charset.StandardCharsets;
//
//@Service
//public class Receiver {
//    private final static String HOST_NAME= "10.0.6.241";
//    private final static String QUEUE_NAME = "nirala_testing_queue";
//
//    public static void main(String[] argv) throws Exception {
//        ConnectionFactory factory = new ConnectionFactory();
//        factory.setHost(HOST_NAME);
//        factory.setPort(5672);
//        factory.setUsername("rabbit_admin");
//        factory.setPassword("Passwordrabbit");
//        factory.setVirtualHost("my_vhost2");
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();
//
//        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
//        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
//
//        final DeliverCallback deliverCallback = (consumerTag, delivery) -> {
//            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
//            System.out.println(" [x] Received '" + message + "'");
//        };
//        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
//        //amqTemplate
//    }
//}