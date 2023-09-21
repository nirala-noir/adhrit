package com.meta.verse.advanceFeaturesInJava.webClient;

import lombok.Data;

@Data
public class Employee {
    long id;
    String name;
    String status;
    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", status=" + status + "]";
    }
}
