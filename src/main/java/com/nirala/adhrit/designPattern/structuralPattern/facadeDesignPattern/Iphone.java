package com.jarvis.edith.designPattern.structuralPattern.facadeDesignPattern;

public class Iphone implements MobileShop{

    @Override
    public void modelNo() {
        System.out.println("Iphone 14 pro max");
    }

    @Override
    public void price() {
        System.out.println("RS: 139000/-");
    }
}
