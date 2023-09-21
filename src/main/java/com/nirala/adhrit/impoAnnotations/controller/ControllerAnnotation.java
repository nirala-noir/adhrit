package com.meta.verse.impoAnnotations.controller;

import com.meta.verse.impoAnnotations.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
@RestController
public class ControllerAnnotation {
    @Autowired
    @Qualifier("object1")
    private Student student;

    @RequestMapping(value = "/home",method = RequestMethod.GET) // using this to hit the endpoint to run this function
    //@ResponseBody // we are using this to get return value as json
    public Student controllerFunc(@RequestBody Student student){
        System.out.println("checking controller class");
        student.study();
        this.student.setName("raman");
        return this.student; // hence prove that this passed as json .. output :{"name":"raman"}
    }


    @RequestMapping(value = "/user/{passedParameter}", method = RequestMethod.GET)
    public String user(@PathVariable("passedParameter") Integer userId){ // passedparameter will assigned to userId variable
        // so by using pathVariable we can get passed parameter from url in java variable
        return String.valueOf(userId);
    }

/*
* @RequestParam: it is used when we pass data from form like: we use INPUT
* Input html tag: <input name="email"/>
* then we can use (@RequestParam("email" string email) instead of (@PathVariable("passedParameter") Integer userId)
*
*
*
* */


}
/*
* whatever data comes in requestBody as a json , here requestBody
* desirialize it into student datatype.
*
*
* earlier -> responseBody doing serialization of whatever data we
* are passing will convert in json at the end
*
*
*
* @RestController-> whenever a controller is returning json then it is
* restcontroller ,
* we can achieve it via responseBody or else is restController->an alternative
*now responseBody is enabled,, we can't see but it is their with every function in this class.
*
*  */