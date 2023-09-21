package com.meta.verse.rabbitMQ.usingAnnotation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderStatus {

    private Order price;
    private String status;//progress,completed
    private String message;
}
