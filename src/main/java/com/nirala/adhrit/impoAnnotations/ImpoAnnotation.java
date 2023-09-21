package com.meta.verse.impoAnnotations;

import com.meta.verse.fetchDataFromPropertyFile.CustomerClassForCoponentScanAnnotationTesting;
import com.meta.verse.impoAnnotations.subPack.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



/*@springbootApplication == @EnableAutoConfiguration + @ComponentScan + @Configuration
*@EnableAutoConfiguration == it trigger libraries of the dependency , which are present in pom.xml and their
* and their libraries present in External libraries -> dependency name folder-> Meta-Inf -> spring.factories ->
* this file contains conditions based on that functionalities activate or deactivate itself.
*
*
* @ComponentScan == it help us to scan all packages & subPackages used in that same class for purpose of bean.
* */
@SpringBootApplication
public class ImpoAnnotation implements CommandLineRunner {
    /*
    * by using @Autowired , it will check in spring container based on DataType of student , if it is found then it will
    * inject to the instance (the object which is present in container) otherwise not found then return error.
    * we can use @Autowired on properties ,setter and constructor ,,, check all possible ?? to use @Autowired
    * */
    @Autowired
    @Qualifier("object2") // we are using this because we have 2 object of same datatype so we differenciate them with a name ,
    // here we are using one of them whose alias is "object1" but still it will cause one problem
    // that is , when we are using @Qualifier("object2") it is still calling object1 so,
    // for this problem we will use @lazy
    //@Lazy  make the object creation when it is needed




    private Student student; /* now what happening is one object of student is already present because of @Bean so, we don't
    new Student() syntax beacuse configuration will catch object from spring container because we have a configuration class where
    we are using a method with @Bean annotation which will create  object and return bean to spring container. they will catch becauese of same DataType
    by this process we are acheiving automatic injection but behind the scene @Configuration and @Bean working to achieve this and helping
    spring container to store the data as preRequisite.

    we will use @Bean inside the @Configuration class ??-> check this
    */
    @Autowired
    ComponentAnnotation componentAnnotation;
    @Autowired
    Customer customer;
//    @Autowired
//    CustomerClassForCoponentScanAnnotationTesting objTemp;
    public static void main(String[] args) {
        SpringApplication.run(ImpoAnnotation.class,args);
        System.out.println("impo annotation");
    }

    @Override
    public void run(String... args) throws Exception {
        this.student.study();
        this.componentAnnotation.getName();//we are not using @Configuration and @Bean to get object
        // we are using @Component , visit respective class for work flow
        //if we will not use then it will throw error because @Autowire will not find any object.
        customer.getName(); // it scanned because it is inside the packege
        //objTemp.getName(); //it is not scanned because it is in another folder/package
        //to over this problem what we will do is, we will declare package name in @Configuration class using @ComponentScan annotation
    }
}
