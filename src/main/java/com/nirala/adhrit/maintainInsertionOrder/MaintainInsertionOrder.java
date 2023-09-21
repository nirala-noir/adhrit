package com.meta.verse.maintainInsertionOrder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MaintainInsertionOrder {
    public static void main(String[] args) {
        SpringApplication.run(MaintainInsertionOrder.class, args);
        InsertionOrderMaintain insertionOrderMaintain= new InsertionOrderMaintain();
        JsonData jsonData= new JsonData();
        insertionOrderMaintain.test(jsonData.data());
    }
}

//run workSpace edith