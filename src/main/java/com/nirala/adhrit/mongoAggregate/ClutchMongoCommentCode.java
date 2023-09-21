package com.meta.verse.mongoAggregate;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class ClutchMongoCommentCode {
    @Autowired
    MongoTemplate mongoTemplate;
    public void misCollectionRefactorToQuarterlyCollection(){
        MongoDatabase db = mongoTemplate.getDb();
        MongoIterable<String> collectionNames = db.listCollectionNames();
        Calendar calendar = Calendar.getInstance();
        String reqReceivedTime ="reqReceivedTime";
        for(String collectionName: collectionNames){
            if(collectionName.indexOf("wmspullt_limited_")==0 && mongoTemplate.getCollection(collectionName).estimatedDocumentCount()>0){
                AggregateIterable<Document> aggregateYear = mongoTemplate.getCollection(collectionName).aggregate(Arrays.asList(new Document("$sort",
                                new Document(reqReceivedTime, 1L)),
                        new Document("$limit", 1L),
                        new Document("$project",
                                new Document(reqReceivedTime, 1L).append("_id", 0L))));
                Date date = (Date) aggregateYear.first().get(reqReceivedTime);
                calendar.setTime(date);
                Integer minYear = calendar.get(Calendar.YEAR);
                Date startDate,endDate;
                while (minYear<2022){
                    //quarter 1
                    calendar.set(minYear,0,1,0,0,0);
                    startDate = calendar.getTime();
                    calendar.set(minYear,2,31,0,0,0);
                    endDate = calendar.getTime();
                    AggregateIterable<Document> matchValue = mongoTemplate.getCollection(collectionName)
                            .aggregate(Arrays.asList(new Document("$match",
                                    new Document(reqReceivedTime,
                                            new Document("$gte", startDate)
                                                    .append("$lte", endDate)))));
                    if(matchValue.first()!=null){
                        String quarterOneCollection = "wa_" + "enterpId_" + 1 + "_" + minYear;
                        AggregateIterable<Document> mergedValue = mongoTemplate.getCollection(collectionName)
                                .aggregate(Arrays.asList(new Document("$match",
                                                new Document(reqReceivedTime,
                                                        new Document("$gte", startDate)
                                                                .append("$lte", endDate))),
                                        new Document("$merge",
                                                new Document("into", quarterOneCollection))));
                    }

                    //quarter 2

                    //qurter 3

                    //quarter 4

                    minYear++;
                }
            }
        }
    }
}
//    public AggregationResults<Document> dataToMove(Date startDate, Date endDate) {
//        MatchOperation matchOperation = Aggregation.match(Criteria.where("breakupDate").gte(startDate).lte(endDate));
//        GroupOperation groupOperation = Aggregation.group("name", "address").count().as("alias");
//        OutOperation outOperation = Aggregation.out("raman_collection");
//        Aggregation aggregation = Aggregation.newAggregation(matchOperation, groupOperation, outOperation);
//        mongoTemplate.getCollection("raman").createIndex(Indexes.ascending("bookName"));
//        AggregationResults<Document> output = mongoTemplate.aggregate(aggregation, MongoCollection.RCS_AGG_SUMMARY.getName(), Document.class);
//        //mongoTemplate.save(output, MongoCollection.APP_ID_INFOS.getName());
////        mongoTemplate.remove(output);
//        return output;
//
//        //use two step 1. find and copy and then
//        //2. remove from mongo
//
//
//    }

//fetch data optimally and set it to dynamic collection generation at runtime
//read and save in dynamic collection
//then delete it from original collection

//    public void dataToMoveInNewCollection(Date startDate , Date endDate){
//        int quarter = startDate.getMonth()/3;//get the sector from 1 to 4
//        String collectionNewName = "dateReturning" + quarter + startDate.getYear();
//        if (mongoTemplate.collectionExists(collectionNewName)) {
//            return;//upsert
//        }// rule for existing collection
//        //case : 4 hits on same date then they all get 12pm data not 4pm data means they should have get updated data
//        //solution for the previous sector collection we will just return if found
//        //if it requiring current date data-collection they we have to fetch everytime data and re-write in same database\
//        //this willgive them updated data of current sector
//
//        mongoTemplate.createCollection(collectionNewName);
//        mongoTemplate.save(dataToMove(startDate,endDate),collectionNewName);
//        mongoTemplate.remove(dataToMove(startDate,endDate));
//
//        //new code template
//        Calendar cal = Calendar.getInstance();
//        int startMonth,endingMonth;
//        long year;
//
//        int month = startDate.getMonth();
//        if(month-3 <=0){
//            endingMonth = 12 -(Math.abs(month-3));
//            quarter = (endingMonth/3);
//            startMonth = quarter*3 + 1;
//            year = startDate.getTime()-1;
//        }
//        else{
//            endingMonth = month-3;
//            quarter = endingMonth/3;
//            startMonth = quarter*3 +1;
//            year = startDate.getYear();
//        }
//        Date startDateToMove = new Date(startDate.getYear()-1,startMonth,1);
//        Date endDateToMove = new Date(startDate.getYear()-1,endingMonth,cal.getActualMaximum(startMonth));
//
//        //fetch all the collection based on the filter of "wmspullt_limited" from "wmspullt_limited_enterpId"
//        for(int i=0;i<10;i++) {
//            MatchOperation matchOperation = Aggregation.match(Criteria.where("reqReceivedTime").gte(startDateToMove).lte(endDateToMove));//matching on the basis of reqReceivedTime
//            OutOperation outOperation = Aggregation.out("wa" + "enterpId" + quarter + year);
//            Aggregation aggregation = Aggregation.newAggregation(matchOperation, outOperation);
//            mongoTemplate.getCollection("current collection under for loop").createIndex(Indexes.ascending("reqReceivedTime"));//fetching to create index before performing aggregation
//            AggregationResults<Document> output = mongoTemplate.aggregate(aggregation, "current collection under for loop", Document.class);
//        }
//
//
//    }
