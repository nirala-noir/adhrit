package com.meta.verse.debugClutch;


import com.google.gson.*;
import org.bson.BSONObject;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


@SpringBootApplication
@RestController
public class PayloadRefactor {
    public static void main(String[] args) {
        SpringApplication.run(PayloadRefactor.class,args);
        System.out.println("Payload Viewer");
        Map<String, Object> map = new HashMap<>();
// Convert a map having list of values.
        String[] value1 = new String[] { "value11", "value12", "value13" };
        String[] value2 = new String[] { "value21", "value22", "value23" };
        map.put("key1", value1);
        map.put("key2", value2);

        JSONObject json = new JSONObject(map);
        System.out.println(json);
    }
    @GetMapping("/ping")
    static String greeting(){
        return "pong";
    }
    @PostMapping("/check")
    static Boolean payloadView(@RequestBody String message){
        JsonObject jsonObj = (JsonObject) new JsonParser().parse(message);
        String data = jsonObj.get("data").toString();

        return true;
    }

    @PostMapping("/payload")
    static Boolean payloadViewer(@RequestBody String message){
        BSONObject bson = com.mongodb.BasicDBObject.parse(message);
        String sender = bson.get("sender").toString();
        JsonObject jsonObj = null;
        String messageType = null;
        JsonArray jsonArray = null;
        String senderEnterprise=null;
        //long start = System.currentTimeMillis();
        try {
            jsonObj = (JsonObject) new JsonParser().parse(message);
            if(jsonObj.get("type")==null) return true;
            messageType = jsonObj.get("type").getAsString();
//            jsonArray = jsonObj.getAsJsonArray("data");
            if(messageType!=null && messageType.equalsIgnoreCase("inboundMessage")){
                senderEnterprise = jsonObj.get("sender")!=null?jsonObj.get("sender").getAsString():null;
            }
            if(jsonObj.get("data")!=null && !jsonObj.get("data").isJsonNull() && jsonObj.get("data").isJsonArray()){
                jsonArray = jsonObj.getAsJsonArray("data");
                saveData(messageType, jsonArray,message,senderEnterprise);
            }
            saveData(messageType, jsonArray,message,senderEnterprise);
            return true;
        } catch (JsonSyntaxException e) {
            return true;
        }finally {

//                logger.debug("processMessages time taken: {}, type: {} msec", timeTaken, messageType);
            jsonObj = null;
            messageType =  null;
            jsonArray = null;
        }
    }
    public static boolean saveData(String messageType, JsonArray jsonArray, String message, String senderEnterprise) {

        if (jsonArray.size() > 0) {
            for (int i = 0; i < jsonArray.size(); i++) {

                try {

                    String value = jsonArray.get(i).toString();
                    WmsPullDlrInfoDBO wmsPullDlrInfoDBO = new Gson().fromJson(value, WmsPullDlrInfoDBO.class);
                    Objects.isNull(wmsPullDlrInfoDBO);
                    System.out.println(wmsPullDlrInfoDBO.getResponseId().getClass().getSimpleName());
                    StringUtils.hasText(wmsPullDlrInfoDBO.getStatus());
                    System.out.println(value);
                }catch(Exception e){
                    return false;
                }
            }
        }
        return true;

    }
}
