package com.jarvis.edith.designPattern.structuralPattern.facadeDesignPattern;

public class Samsung implements MobileShop{

    @Override
    public void modelNo() {
        System.out.println("Samsunga galaxy s22 ultra");
    }

    @Override
    public void price() {
        System.out.println("RS: 110000/-");
    }
}
