package com.meta.verse.designPattern.creationalPattern.builderDesignPatter;

public class Sony extends Company{

    @Override
    public String pack() {
        return "Sony CD";
    }

    @Override
    public int price() {
        return 20;
    }

}
