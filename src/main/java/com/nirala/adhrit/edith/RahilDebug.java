package com.meta.verse.edith;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.meta.verse.VerseApplication;
import com.mongodb.BasicDBObject;
import lombok.extern.slf4j.Slf4j;
import org.bson.BSONObject;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@SpringBootApplication
public class RahilDebug {
    public static void main(String[] args) {
        SpringApplication.run(RahilDebug.class, args);
        Document jo = new Document();
        jo.put("msisdn", "John");
        jo.put("msisdnMasked", "Smith");
        jo.put("appName", 25);
        
        String payload = new Gson().toJson(jo);

        BSONObject bson = BasicDBObject.parse(payload);
        bson.get("name");
        UserOptInDBO userOptInDBO = new Gson().fromJson(payload, UserOptInDBO.class);
        System.out.println();
    }
}
