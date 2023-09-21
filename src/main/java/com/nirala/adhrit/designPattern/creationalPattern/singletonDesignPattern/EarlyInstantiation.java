package com.jarvis.edith.designPattern.creationalPattern.singletonDesignPattern;

public class EarlyInstantiation {
    /*to create singleton class we need
     *1.static member of class:It gets memory only once because of static, it contains the instance of the Singleton class.
     * 2.private constructor:It will prevent to instantiate the Singleton class from outside the class.
     * 3.static factory method:This provides the global point of access to the Singleton object and returns the instance to the caller.
     *
     *  */

    private static EarlyInstantiation object = new EarlyInstantiation();//early instance
    //static member=> gets memory only once because of static, itcontains the instance of the Singleton class.
    private EarlyInstantiation(){}; // It will prevent to instantiate the Singleton class from outside the class.
    public static EarlyInstantiation getObject(){
        //This provides the global point of access to the Singleton object
        // and returns the instance to the caller.
        return object;
    }
    public void workingFunction(){
        //write code here
    }
}
