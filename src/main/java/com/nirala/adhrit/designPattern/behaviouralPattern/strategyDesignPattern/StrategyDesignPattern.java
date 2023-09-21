package com.meta.verse.designPattern.behaviouralPattern.strategyDesignPattern;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StrategyDesignPattern {
    public static void main(String[] args) throws IOException {
        System.out.println("Strategy design pattern");
        /*A Strategy Pattern says that "defines a family of functionality, encapsulate each one,
        and make them interchangeable".The Strategy Pattern is also known as Policy.*/


        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the first value: ");
        float value1=Float.parseFloat(br.readLine());
        System.out.print("Enter the second value: ");
        float value2=Float.parseFloat(br.readLine());
        Context context = new Context(new Addition());
        System.out.println("Addition = " + context.executeStrategy(value1, value2));

        context = new Context(new Substraction());
        System.out.println("Subtraction = " + context.executeStrategy(value1, value2));

        context = new Context(new Multiplication());
        System.out.println("Multiplication = " + context.executeStrategy(value1, value2));
    }
}
