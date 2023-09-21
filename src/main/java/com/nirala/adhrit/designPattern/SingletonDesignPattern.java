package com.meta.verse.designPattern;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SingletonDesignPattern {
    public static void main(String[] args) {
        SpringApplication.run(SingletonDesignPattern.class,args);
        System.out.println("singleton design pattern");
    }
}
