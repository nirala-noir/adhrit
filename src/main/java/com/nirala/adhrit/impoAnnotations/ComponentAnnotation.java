package com.meta.verse.impoAnnotations;

import org.springframework.stereotype.Component;

/*
* when we are usinf @SpringBootApplication -> it contains @ComponentScan then it will scan all the libraries
* and if we are using @Autowired for ComponentAnnotation class then @Autowired will try to find the object in spring
* container , but it will not find there because we haven't created ,till here @componentScan try to get.
* so if we use @Component annotation on a class , then @ComponentScan will come up to this class and get @Component
* it will understand that it is also part of flow so,@ComponentScan have to maintain its life cycle so,
* it will generate it object to maintain life cycle and that object will get @Autowired annotation
* */

@Component
public class ComponentAnnotation {
    public void getName(){
        System.out.println("my name is alpha");
    }
}
/*
* @Component has several specific annotation inside it.
* such as: @Controller //use for presentation layer class // it will work for component also it will provide feature of mvc controller
*                   (Component, mvc controller)
*
*           @Service // we use on business logics, means service class
*           @Repository // we use this on dao class , means component + dao facilities such as: exception handle as unchecked throw
*
* */