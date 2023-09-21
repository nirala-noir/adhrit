package com.jarvis.edith.designPattern.structuralPattern.facadeDesignPattern;

public class Blackberry implements MobileShop{

    @Override
    public void modelNo() {
        System.out.println("BlackBerry 14 pro max");
    }

    @Override
    public void price() {
        System.out.println("RS: 129000/-");
    }
}
