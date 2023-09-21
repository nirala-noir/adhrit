package com.meta.verse.IOCwithNewBeanWorkFlow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class IOCmain {
    public static void main(String[] args) {
//        System.out.println("IOC practice");
//        Sim sim = new Airtel();
//        sim.simName();
//        sim.simCompany();

        /*NOTE: these above will work fine but what if we add an new class named vodafone
        then we have to again create object and call so,
        we don't want like this ,, we want to call vodafone from outside of code.. from where we make change
        and it will auto create object and call the function

        for that:: below process are
        */
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Sim sim = applicationContext.getBean("sim2", Sim.class);
        sim.simName();
        sim.simCompany();
/*
* here we don't have to create jio object or other class object which implement and we want as a instance
* so, we are declaring in resources-> beans.xml which is our custome beans and from there we are generating objct
* and next time we will put vodafone in beans and we will access all the function. beans.xml is in resources folder
*
* we can also add property from beans which can be used here watch springboot complete tutorial
*
* //COMPLETED
* */

    }
}
//reference link: https://www.geeksforgeeks.org/spring-understanding-inversion-of-control-with-example/#:~:text=Spring%20IoC%20(Inversion%20of%20Control,that%20make%20up%20the%20application.