package com.meta.verse.impoAnnotations;

public class  Student {
    String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void study(){
        System.out.println("student are studying");
    }
}
