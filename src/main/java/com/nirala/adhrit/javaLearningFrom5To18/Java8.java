package com.meta.verse.javaLearningFrom5To18;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Java8 {
    public static void main(String[] args) {
        System.out.println("java 8");
    }
    void test(){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        //feature:1
        //iterate with enhanced for loop
        //iterate with foreach
        //iterate with iterator

        for (Integer item:list){
            System.out.println(item);
        }

        list.forEach(item-> System.out.println(item));

        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }

        //feature:2
        //We can use default and static keyword to create interfaces with method implementation.

        //feature:3->  Functional Interfaces and Lambda Expressions
        /*Runnable r = new Runnable(){
                                    @Override
                                    public void run() {
                                        System.out.println("My Runnable");
                                    }};

			change into:
			Runnable r1 = () -> {
			                        System.out.println("My Runnable");
		                        };

		    If you have single statement in method implementation, we don’t need curly braces also.
		    For example above Interface1 anonymous class can be instantiated using lambda as follows:
		    Runnable r1 = () -> {
                                    System.out.println("My Runnable");
                                };

            changes into:
            Interface1 i1 = (s) -> System.out.println(s);
		        i1.method1("abc");

			*/
            //feature :4-> stream api-> java.util.stream.Stream
            //feature 5: -> time api -> java.time
            //feature 6,7,8->  Collection API improvements, Concurrency API improvements, Java IO improvements


    }
}
