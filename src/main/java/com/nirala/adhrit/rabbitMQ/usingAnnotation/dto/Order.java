package com.meta.verse.rabbitMQ.usingAnnotation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {

    private String id;
    private String to;
    private String template_code;
    private String bot_id;
//    Map<String,String> custom_param = new HashMap<>();
//    private String name;
//    private int qty;
//    private List<String> price = new ArrayList<>();
    String custom_param;
}
