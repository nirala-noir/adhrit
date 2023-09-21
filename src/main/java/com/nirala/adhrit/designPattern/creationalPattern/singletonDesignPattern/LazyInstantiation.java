package com.jarvis.edith.designPattern.creationalPattern.singletonDesignPattern;

public class LazyInstantiation {
    private static LazyInstantiation object;

    LazyInstantiation(){};

    private static LazyInstantiation getLazyInstantiation(){
        if(object==null){
//            synchronized (Singleton.class){
//                if (object==null){
//                    object = new Singleton();
//                }
//            }
            object= new LazyInstantiation();
        }
        return object;
    }

    public void workingFunction(){
        //write code here
    }
}
