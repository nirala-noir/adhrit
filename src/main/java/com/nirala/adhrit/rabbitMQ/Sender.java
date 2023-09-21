package com.meta.verse.rabbitMQ;
import com.meta.verse.VerseApplication;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

//amqp-> advanced message queuing protocol -> open source message broker
// message broker
@Component
@SpringBootApplication
public class Sender {
//    @Autowired
//    private static RabbitTemplate template;
    private final static String HOST_NAME= "10.0.6.241";
    private final static String QUEUE_NAME = "nirala_testing_queue";

    public static void main(String[] args) throws Exception {
        //SpringApplication.run(Sender.class, args);
        final ConnectionFactory factory = new ConnectionFactory();
        //NOTE: https://docs.oracle.com/javaee/7/api/javax/jms/ConnectionFactory.html
        //NOTE: https://jstobigdata.com/rabbitmq/rabbitmq-with-spring-amqp-and-spring-boot/
        //
        factory.setHost(HOST_NAME);
        factory.setPort(5672);
        factory.setUsername("rabbit_admin");
        factory.setPassword("Passwordrabbit");
        factory.setVirtualHost("my_vhost2");
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            for (int i = 0; i < 10; i++) {
                final String message = "Hello World!"+i;
                System.out.println(" [x] Sent '" + message + "'");
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes(StandardCharsets.UTF_8));
            }
        }
    }
    @RabbitListener(queues = Sender.QUEUE_NAME) //check this format
    public void listener(String message){
        System.out.println(message);
    }
}