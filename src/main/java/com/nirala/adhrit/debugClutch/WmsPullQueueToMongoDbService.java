package com.meta.verse.debugClutch;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class WmsPullQueueToMongoDbService {
    public static final Logger logger = LoggerFactory.getLogger(WmsPullQueueToMongoDbService.class);
    @Autowired
    MongoTemplate mongoTemplate;
    public void processMessages(String message){
        //message :{}
        JsonObject jsonObj = (JsonObject) new JsonParser().parse(message);
        JsonArray jsonArray= null;
        if(jsonObj.has("sender") && jsonObj.has("channel")){
            if(jsonObj.get("sender").getAsString().equals("") || jsonObj.get("channel").getAsString().equals("")){//checked sender and channel
                logger.info("sender or channel is empty string");
                return;
            }

            if((jsonObj.has("type") && jsonObj.get("type").getAsString().equals(""))){ //check type key
                saveCrashedData(jsonObj);//saving crashing data
            }
            if(jsonObj.has("data")){//checking data key
                    JsonObject jsonObject = jsonObj.getAsJsonObject("data");
                    if(jsonObject.get("msidn").isJsonArray()) {
                        jsonArray = jsonObject.getAsJsonArray("msidn");
                        if (jsonArray.size() == 0) {
                            saveCrashedData(jsonObj);//saving crashing data
                            return;
                        }
                        else{
                            //push in sender_channel_optin by extracting this array up to size.
                            //jsonArray containing msisdn numbers
                            for(int i=0;i<jsonArray.size();i++) {
                                saveData(jsonArray.get(i).getAsString(),jsonObj.get("sender").getAsString(),jsonObj.get("channel").getAsString(),jsonObj.get("type").getAsString());
                            }
                        }
                    }
                    else if(jsonObject.get("msidn").getAsString().equals("")){
                        saveCrashedData(jsonObj);//saving crashing data
                        return;
                    }
            }
            //push in mongo by creating collection name as sender_channel_optin
            saveData(jsonObj.get("msisdn").getAsString(),jsonObj.get("sender").getAsString(),jsonObj.get("channel").getAsString(),jsonObj.get("type").getAsString());
        }
    }
    public void saveData(String msisdn, String sender, String channel, String type){
        /*
        format this document as
        msisdn:[sender, type, receiveTime]
        */
        //find and insert not only create and insert
        String collection = sender+"_"+channel;
        //mongoTemplate.getCollection(collection).updateOne(new BsonDocument());
        DBObject findQuery = new BasicDBObject("Sender", sender);
        DBObject listItem = new BasicDBObject("Data", new BasicDBObject("msisdn",msisdn).append("type",type).append("date",new Date()));
        DBObject updateQuery = new BasicDBObject("$push", listItem);
        mongoTemplate.getCollection("raman").updateOne((Bson) findQuery, (Bson) updateQuery);
        //myCol.update(findQuery, updateQuery);
        /*"_id" : 1,
            "scores" : [
                {
                    "type" : "homework",
                    "score" : 78.97979
                },
                {
                    "type" : "homework",
                    "score" : 6.99
                },
                {
                    "type" : "quiz",
                    "score" : 99
                }
            ]*/

        Document data = new Document();
        JsonArray jsonArray = new JsonArray();
        data.put("Sender",sender);
        Map map= new HashMap(4);
        map.put("msisdn", msisdn);
        map.put("type", type);
        map.put("date", new Date());
//        jsonArray.add(map);
        data.put("Data",jsonArray);

        mongoTemplate.getCollection(collection).insertOne((org.bson.Document) data);
    }
    public void saveCrashedData(JsonObject jsonObject){
        String collection = "sender_channel";
        mongoTemplate.getCollection(collection).insertOne(new org.bson.Document());
    }

}
