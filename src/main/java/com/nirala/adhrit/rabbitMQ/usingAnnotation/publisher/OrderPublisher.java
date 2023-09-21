package com.meta.verse.rabbitMQ.usingAnnotation.publisher;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.meta.verse.rabbitMQ.usingAnnotation.config.MessagingConfig;
import com.meta.verse.rabbitMQ.usingAnnotation.dto.Order;
import com.meta.verse.rabbitMQ.usingAnnotation.dto.OrderStatus;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate template;
    @GetMapping("/ping")
    public String messageCheck(){
        return "welcome";
    }

    @PostMapping("/{restaurantName}")
    public String bookOrder(@RequestBody Order order, @PathVariable String restaurantName) {
//
//        String payload = new Gson().toJson(order);
//        JsonObject jsonObj = (JsonObject) new JsonParser().parse(payload);
//        JsonArray jsonArray = null;
//        jsonArray= jsonObj.getAsJsonArray("price");
//        if (jsonObj.has("price") && jsonObj.get("price").getAsString().equals("")) {
//            System.out.println("raman");
//        }
        //restaurantservice
        //payment service
        //OrderStatus orderStatus = new OrderStatus(order, "PROCESS", "order placed succesfully in " + restaurantName);
        //template.convertAndSend(MessagingConfig.EXCHANGE, MessagingConfig.ROUTING_KEY, order);
        System.out.println(order.getId()+ " "+order.getBot_id()+ " "+order.getTo()+ " "+order.getTemplate_code());

        JsonObject jsonObj = (JsonObject) new JsonParser().parse(order.getCustom_param());
        JSONObject msgObj = new JSONObject(order.getCustom_param());
        System.out.println(msgObj.get("var1_in_title2")+" checked");

       // JSONObject jsonObject = (JSONObject) new JSONParser().parse(order.getCustom_param());



        System.out.println(jsonObj.get("var1_in_title2").getAsString());

        return "Success !!";
    }
}

/*curl --location --request POST 'http://localhost:8080/order/nirala_cafeteria' \
--header 'Content-Type: application/json' \
--data-raw '{
    "orderId":"4",
    "name":"rahul",
    "qty":6,
    "price":150.50
}'*/