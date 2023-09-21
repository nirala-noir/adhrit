package com.meta.verse.mongoAggregate;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Component
@SpringBootApplication
public class MongoTemplateUse {
    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        MongoTemplateUse.mongoTemplate = mongoTemplate;
    }

    static private MongoTemplate mongoTemplate;

    public static void main(String[] args) {
        SpringApplication.run(MongoTemplateUse.class, args);
        long analytics = mongoTemplate.getCollection("transactions").estimatedDocumentCount();
        AggregateIterable<Document> aggregateYear = mongoTemplate.getCollection("transactions").aggregate(Arrays.asList(new Document("$sort",
                        new Document("bucket_start_date", 1L)),
                        new Document("$limit", 1L)));
//                        new Document("$project",
//                        new Document("bucket_start_date", 1L).append("_id", 0L))));
        Date date = (Date) aggregateYear.first().get("bucket_start_date");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        Integer minYear= calendar.get(Calendar.YEAR);
        calendar.set(minYear,1,1,0,0,0);
        Date startDate1 = calendar.getTime();
        System.out.println(date.getMonth()+1 +" "+ 19+date.getYear());
//        while(minYear<2022){
            //quarter 1
//            if(date.getMonth()/3==0){
                Date startDate =new Date(minYear-1900, date.getMonth(),1,0,0,0 );
                Date endDate = new Date(minYear-1900, date.getMonth()+2, 31,23,59,59);
                //aggregation type of findong and merging in new collection
                String collectionName ="wa_" + "enterpId_" +"1"+"_" + minYear;
                AggregateIterable<Document> aggregateValue2 = mongoTemplate.getCollection("transactions").aggregate(Arrays.asList(new Document("$match",
                        new Document("bucket_start_date",
                                new Document("$gte", startDate)
                                        .append("$lte", endDate)))));
        Document firstDoc = aggregateValue2.first();
        boolean empty = firstDoc==null?true:false;
        minYear++;
        Document first = mongoTemplate.getCollection("transactions").aggregate(Arrays.asList(new Document("$match",
                        new Document("bucket_start_date",
                                new Document("$gte", startDate)
                                        .append("$lte", endDate))),
                new Document("$merge",
                        new Document("into", collectionName)))).first();

        //above
//                MatchOperation matchOperation = Aggregation.match(Criteria.where("bucket_start_date").gte(startDate).lte(endDate));
//                aggregateValue.first();
                //perform a aggregation query here and check output ,, if exist values then create new collection and move using merge
                //Aggregation aggregation = Aggregation.newAggregation(matchOperation);
                //AggregationResults<Document> output = mongoTemplate.aggregate(aggregation, "transactions", Document.class);
                // you will end up creating empty collections

        //if this output contains any value then below line should executed is and optimal solution .. otherwise
//                OutOperation outOperation = Aggregation.out(collectionName);
//                Aggregation aggregation = Aggregation.newAggregation(matchOperation, outOperation);
//                mongoTemplate.aggregate(aggregation, "transactions", Document.class);

//        MongoDatabase db = mongoTemplate.getDb();
//        MongoIterable<String> collectionNames = db.listCollectionNames();
//        Calendar calendar = Calendar.getInstance();
//        String reqReceivedTime ="bucket_start_date";
//        for(String collectionName: collectionNames){
//            if(collectionName.indexOf("transactions")==0 && mongoTemplate.getCollection(collectionName).estimatedDocumentCount()>0){
//                AggregateIterable<Document> aggregateYear = mongoTemplate.getCollection(collectionName).aggregate(Arrays.asList(new Document("$sort",
//                                new Document(reqReceivedTime, 1L)),
//                        new Document("$limit", 1L),
//                        new Document("$project",
//                                new Document(reqReceivedTime, 1L).append("_id", 0L))));
//                Date date = (Date) aggregateYear.first().get(reqReceivedTime);
//                calendar.setTime(date);
//                Integer minYear = calendar.get(Calendar.YEAR);
//                Date startDate,endDate;
//                while (minYear<2022){
//                    //quarter 1
//                    calendar.set(minYear,0,1,0,0,0);
//                    startDate = calendar.getTime();
//                    calendar.set(minYear,2,31,0,0,0);
//                    endDate = calendar.getTime();
//                    AggregateIterable<Document> matchValue = mongoTemplate.getCollection(collectionName)
//                            .aggregate(Arrays.asList(new Document("$match",
//                                    new Document(reqReceivedTime,
//                                            new Document("$gte", startDate)
//                                                    .append("$lte", endDate)))));
//                    if(matchValue.first()!=null){
//                        String quarterOneCollection = "wa_" + "enterpId_" + 1 + "_" + minYear;
//                        AggregateIterable<Document> mergedValue = mongoTemplate.getCollection(collectionName)
//                                .aggregate(Arrays.asList(new Document("$match",
//                                                new Document(reqReceivedTime,
//                                                        new Document("$gte", startDate)
//                                                                .append("$lte", endDate))),
//                                        new Document("$merge",
//                                                new Document("into", quarterOneCollection))));
//                        System.out.println("collection created");
//                    }
//
//                    //quarter 2
//
//                    //qurter 3
//
//                    //quarter 4
//
//                    minYear++;
//                }
//            }
//        }
//
    }
}

    /*
     * Requires the MongoDB Java Driver.
     * https://mongodb.github.io/mongo-java-driver
     */

//    MongoClient mongoClient = new MongoClient(
//            new MongoClientURI(
//                    "mongodb+srv://raman_nirala:xjIjKZVYwtUiVkJA@cluster0.vu7ww3s.mongodb.net/test?tls=true"
//            )
//    );
//
//
//    FindIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$sort",
//                    new Document("bucket_start_date", 1L)),
//            new Document("$merge",
//                    new Document("into", "string")
//                            .append("on", "string")
//                            .append("let", "specification(s)")
//                            .append("whenMatched", "string")
//                            .append("whenNotMatched", "string"))));
//    /*
//     * Requires the MongoDB Java Driver.
//     * https://mongodb.github.io/mongo-java-driver
//     */
//
//    MongoClient mongoClient = new MongoClient(
//            new MongoClientURI(
//                    "mongodb+srv://raman_nirala:xjIjKZVYwtUiVkJA@cluster0.vu7ww3s.mongodb.net/test?tls=true"
//            )
//    );
//    MongoDatabase database = mongoClient.getDatabase("sample_analytics");
//    MongoCollection<Document> collection = database.getCollection("transactions");
//
//    FindIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
//            new Document("transaction_count",
//                    new Document("$gte", 81L)
//                            .append("$lte", 150L)))
//            new Document("$merge",
//                    new Document("into", "string")
//                    .append("on", "string")
//                    .append("let", "specification(s)")
//                    .append("whenMatched", "string")
//                    .append("whenNotMatched", "string"))));

/*
* /*
 * Requires the MongoDB Java Driver.
 * https://mongodb.github.io/mongo-java-driver
 */
/*
    MongoClient mongoClient = new MongoClient(
            new MongoClientURI(
                    "mongodb+srv://raman_nirala:xjIjKZVYwtUiVkJA@cluster0.vu7ww3s.mongodb.net/test?tls=true"
            )
    );
    MongoDatabase database = mongoClient.getDatabase("sample_analytics");
    MongoCollection<Document> collection = database.getCollection("transactions");

    FindIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$match",
                    new Document("bucket_start_date",
                            new Document("$gte",
                                    new java.util.Date(925084800000L))
                                    .append("$lte",
                                            new java.util.Date(1650931200000L)))),
            new Document("$merge",
                    new Document("into", "wa_enterpId_1_1962"))));
    */