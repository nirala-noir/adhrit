package com.meta.verse.designPattern.behaviouralPattern.observerDesignPattern;

public class ObserverDesignPattern {
    public static void main(String[] args) {
        System.out.println("observer design pattern");
        /*
        * An Observer Pattern says that "just define a one-to-one dependency so that when one object changes state,
        *  all its dependents are notified and updated automatically".
           The observer pattern is also known as Dependents or Publish-Subscribe.*/

        // create an event source - reads from stdin
        final EventSource eventSource = new EventSource();

        // create first observer
        final ResponseHandler responseHandler1 = new ResponseHandler();

        // subscribe the observer to the event source
        eventSource.addObserver(responseHandler1);


        // create second observer
        final ResponseHandler2 responseHandler2 = new ResponseHandler2();

        // subscribe the observer to the event source
        eventSource.addObserver(responseHandler2);

        // starts the event thread
        Thread thread = new Thread(eventSource);
        thread.start();
    }
}
