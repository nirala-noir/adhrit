package com.meta.verse.designPattern.creationalPattern.factoryDesignPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class FactoryMethodDesignPattern {
    public static void main(String[] args) throws IOException {
        System.out.println("FactoryMethodDesignPattern");
        /*
        * A Factory Pattern or Factory Method Pattern says that just define an interface
        * or abstract class for creating an object but let the subclasses decide which class to instantiate.
        * In other words, subclasses are responsible to create the instance of the class.
        * The Factory Method Pattern is also known as Virtual Constructor.*/

        /*
        * Factory Method Pattern allows the sub-classes to choose the type of objects to create.
        * It promotes the loose-coupling by eliminating the need to bind application-specific classes into the code.
        *
         * reference: https://www.javatpoint.com/factory-method-design-pattern
         * */
        GetPlanFatory getPlanFatory = new GetPlanFatory();

        System.out.print("Enter the name of plan for which the bill will be generated: ");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String planName = br.readLine();
        System.out.print("Enter the number of units for bill will be calculated: ");
        int units = Integer.parseInt(br.readLine());

        Plan p = getPlanFatory.getPlan(planName);
        //call getRate() method and calculateBill()method of DomesticPaln.

        System.out.print("Bill amount for "+planName+" of  "+units+" units is: ");
        p.getRate();
        p.calculateBill(units);
    }
}

/* impo
* Usage of Factory Design Pattern
1. When a class doesn't know what subclasses will be required to create
2. When a class wants that its subclasses specify the objects to be created.
3. When the parent classes choose the creation of objects to its subclasses.
* */