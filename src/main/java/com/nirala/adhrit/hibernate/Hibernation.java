package com.meta.verse.hibernate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Hibernation {
    public static void main(String[] args) {
        SpringApplication.run(Hibernation.class,args);
        System.out.println("hibernate model generation");
    }
}
//hibernate is a java framework that simplifies the development of java app to interact with the database.
/*it solve the issue comes in jdbc , means writing sql query in jdbc we don't want to write
* ORM-> hibernate is ORM tool (object relational mapping)
* we insert data in database in form of object in java
*
* */