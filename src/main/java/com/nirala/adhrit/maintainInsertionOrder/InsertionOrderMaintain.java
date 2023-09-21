package com.meta.verse.maintainInsertionOrder;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.FindOneAndUpdateOptions;
import com.mongodb.client.model.ReturnDocument;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class InsertionOrderMaintain {
    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        InsertionOrderMaintain.mongoTemplate = mongoTemplate;
    }
    static private MongoTemplate mongoTemplate;
    public void test(Document data){
        MongoCollection<Document> collecton = mongoTemplate.getCollection("temp456");
        Bson filter = Filters.eq("sender", "niralA");
//        JSONArray ja = new JSONArray();
        Map m = new LinkedHashMap(2);
        m.put("type", "home");
        m.put("number", "212 555-1234");
        m.put("recieveTime",new Date());
//        ja.add(m);
        Bson update = Updates.push("Data", m);
        //UpdateOptions options2 = new UpdateOptions().upsert(true);
        FindOneAndUpdateOptions options = new FindOneAndUpdateOptions().returnDocument(ReturnDocument.AFTER).upsert(true);
        collecton.findOneAndUpdate(filter,update,options);


        System.out.println("successfully inserted");

        //fetch and see insertion order maintained or not
        //{"data":"{"msisdn":"919162466682"}","sender":"918010924190","type":"optin"}

    }

}
