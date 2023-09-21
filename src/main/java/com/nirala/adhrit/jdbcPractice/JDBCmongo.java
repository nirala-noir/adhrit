package com.meta.verse.jdbcPractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JDBCmongo {
    public static void main(String[] args) {
        SpringApplication.run(JDBCmongo.class,args);
        System.out.println("run jdbc");
    }
}
