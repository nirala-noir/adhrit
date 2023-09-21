package com.meta.verse.multithreading;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultipleThread {
    public static void main(String[] args) {
        SpringApplication.run(MultipleThread.class,args);
        System.out.println("multi thread work flow");
    }
}
