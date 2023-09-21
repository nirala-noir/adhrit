package com.meta.verse.javaLearningFrom5To18;

public class SuperAndThis {
    public static void main(String[] args) {
        //as super and this can be used as a first statement
        //Java requires that if you call this() or super() in a constructor, it must be the first statement.
        Child obj = new Child();
       // System.out.println( new B().m1()  ); //=> B.m1() -> A.m1() -> B.m3() -> 4.
        //bhari error check this flow of child and parent constructor.
    }
}
class A
{
    int m1() {return m3();}
    int m2() {return this.m3();}
    int m3() {return 1;}
}

class B extends A
{
    int m1() {return super.m2();}
    int m3() {return 4;}
    int m4() {return m2();}
}

class Parent {

    // parent class constructor
//    Parent() {
//        //this(); // we can't do this because it will throws recursive call error
//        this(5); // we can do this if we have another constructor with different signature
//        System.out.println("Hi I am Parent class constructor.");
//    }
    Parent(int x){
        //this(); // error recusive call
        System.out.println(x);
    }
}

// child class extending parent class
class Child extends Parent {

    // child class constructor
    Child() {
        super(8);
        // invoking parent class constructor
        //super(); // this is correct format , as this first statement of this function
        System.out.println("raman");
        //super(); // we can't do this we have to replace it just below the function, as to amke first statement
    }
}


/*
How to not call parent class constructor Java?
There is absolutely no way to do this in Java; it would break the language specification.
 Just before a reference to the newly created object is returned as the result,
 the indicated constructor is processed to initialize the new object using the following procedure
1. Assign the arguments for the constructor [...]
2.If this constructor begins with an explicit constructor invocation of another constructor in the same class (using this), then [...]
3.This constructor does not begin with an explicit constructor invocation of another constructor in the same class (using this). If this constructor is for a class other than Object, then this constructor will begin with an explicit or implicit invocation of a superclass constructor (using super).
4.Execute the instance initializers and instance variable initializers for this class [...]
5.Execute the rest of the body of this constructor [...]
* */