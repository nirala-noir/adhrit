package com.meta.verse.javaVerse;

import org.json.JSONObject;

public class VerseOfJava {
    public static void main(String[] args) {
        System.out.println("features in java");
        JSONObject jsonObject = new JSONObject();
        JSONObject jsonObject1= new JSONObject();
        jsonObject1.put("name","raman");
        jsonObject1.put("roll",98);
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("fr_name","nirala");
        jsonObject2.put("his_roll",100);
        jsonObject1.put("jsonObject2",jsonObject2);
        jsonObject.put("my_detail",jsonObject1);
        jsonObject.put("fr_detail",jsonObject2);

        System.out.println(jsonObject);

    }
}
/*
* Introduction to Advanced Java
JDBC
What is JDBC?
JDBC Architecture
Steps to create JDBC Application
JDBC Driver Types & Connections
Java Servlets
Introduction to Java Servlets
Servlet Life Cycle
Steps to create Servlet
Session Tracking in Servlets
 JSP
Introduction to JSP
Life Cycle of JSP
JSP Scripting Elements
* */
