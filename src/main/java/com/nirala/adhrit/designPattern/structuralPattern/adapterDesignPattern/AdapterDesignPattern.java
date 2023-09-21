package com.jarvis.edith.designPattern.structuralPattern.adapterDesignPattern;

public class AdapterDesignPattern {
    public static void main(String[] args) {
        System.out.println("Adapter design pattern");
        CreditCard targetInterface=new BankCustomer();
        targetInterface.giveBankDetails();
        System.out.print(targetInterface.getCreditCard());
    }
}
