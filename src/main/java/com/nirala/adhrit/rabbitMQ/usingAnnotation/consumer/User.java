package com.meta.verse.rabbitMQ.usingAnnotation.consumer;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.meta.verse.rabbitMQ.usingAnnotation.config.MessagingConfig;
import com.meta.verse.rabbitMQ.usingAnnotation.dto.Order;
import com.meta.verse.rabbitMQ.usingAnnotation.dto.OrderStatus;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
public class User {

    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueue(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println(message.getPayload());
        String payload = new Gson().toJson(message.getPayload());
//        JsonObject jsonObj= (JsonObject) new JsonParser().parse(payload);
//        JsonArray jsonArray=null;
//        if(jsonObj.get("price").isJsonArray()){
//            jsonArray= jsonObj.getAsJsonArray("price");
//            for(int i=0;i<jsonArray.size();i++){
//                System.out.println(jsonArray.get(i));
//                String msisdn = jsonArray.get(i).toString();
//                OrderStatus dtoValue = new Gson().fromJson(msisdn, OrderStatus.class);
//            }
//        }
        System.out.println("raman");
        System.out.println(payload);

        channel.basicAck(tag, false);


        //System.out.println(payload);
    }
//    @RabbitListener(queues = MessagingConfig.QUEUE)
    public void consumeMessageFromQueueTesting(Message message) {
        //if exception happen
        //consume only 5 message

    }
}
