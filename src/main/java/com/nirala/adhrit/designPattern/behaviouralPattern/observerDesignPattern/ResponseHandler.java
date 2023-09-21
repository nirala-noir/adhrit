package com.meta.verse.designPattern.behaviouralPattern.observerDesignPattern;

import java.util.Observable;
import java.util.Observer;

public class ResponseHandler implements Observer {
    private String resp;
    @Override
    public void update(Observable o, Object arg) {
        if (arg instanceof String) {
            resp = (String) arg;
            System.out.println("\nReceived Response: " + resp );
        }
    }
}
