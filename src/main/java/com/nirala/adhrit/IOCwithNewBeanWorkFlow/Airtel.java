package com.meta.verse.IOCwithNewBeanWorkFlow;

public class Airtel implements Sim{
    @Override
    public void simName() {
        System.out.println("prepaid");
    }

    @Override
    public void simCompany() {
        System.out.println("Airtel");
    }
}
