package com.meta.verse.designPattern.creationalPattern.builderDesignPatter;

public class BuilderDesignPattern {
    public static void main(String[] args) {
        System.out.println("Builder Pattern says that\n " +
                "construct a complex object from simple objects using step-by-step approach");
        /*
        * It is mostly used when object can't be created in single step
        * like in the de-serialization of a complex object
        * refernce: https://www.javatpoint.com/builder-design-pattern
        * */


        CDBuilder cdBuilder=new CDBuilder();
        CDType cdType1=cdBuilder.buildSonyCD();
        cdType1.showItems();

        CDType cdType2=cdBuilder.buildSamsungCD();
        cdType2.showItems();

    }
}
