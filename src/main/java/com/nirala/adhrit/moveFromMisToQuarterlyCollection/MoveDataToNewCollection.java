package com.meta.verse.moveFromMisToQuarterlyCollection;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Slf4j
@Component
@SpringBootApplication
public class MoveDataToNewCollection {
    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        MoveDataToNewCollection.mongoTemplate = mongoTemplate;
    }

    static private MongoTemplate mongoTemplate;
    public static String reqReceivedTime ="reqReceivedTime";
    public static final String collectionIdentifier = "clutchMISData";

    public static void main(String[] args) {
        SpringApplication.run(Clutch.class, args);
        log.info("started application");
        try {
            for (String collectionName : collectionsFromDatabase()) {
                if (collectionName.indexOf(collectionIdentifier) == 0 && mongoTemplate.getCollection(collectionName).estimatedDocumentCount() > 0) {

                    Date minDateInCollection = minDateFromCollection(collectionName).getTime();
                    //Date minDateInCollection = customDate();
                    Date currentMinus120Date = currentMinus120Date();


                    while(minDateInCollection.before(currentMinus120Date)){
                        mergeIntoQuarterlyCollection(collectionName,minDateInCollection,currentMinus120Date);
                        minDateInCollection = incrementByOneQuarter(minDateInCollection);
                    }
                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        log.info("ended application");

    }

    private static Date incrementByOneQuarter(Date minDateInCollection) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(minDateInCollection);
//        System.out.println(calendar.getTime());

        calendar.add(Calendar.MONTH,3);

//        System.out.println(calendar.getTime());
//        System.out.println();
        return calendar.getTime();

    }

    private static Calendar minDateFromCollection(String collectionName) {
        Calendar calendar = Calendar.getInstance();
        AggregateIterable<Document> aggregateYear = mongoTemplate
                .getCollection(collectionName).aggregate(Arrays.asList(new Document("$group",
                        new Document("_id", "_id")
                                .append("minDate",
                                        new Document("$min", "$bucket_start_date")))));
        Date date = (Date) aggregateYear.first().get("minDate");
        calendar.setTime(date);
        return calendar;
    }
    public static Date currentMinus120Date(){
        Calendar currentMinusFour = Calendar.getInstance();
        currentMinusFour.setTime(new Date());
        currentMinusFour.add(Calendar.MONTH,-3);
        currentMinusFour.set(currentMinusFour.get(Calendar.YEAR),currentMinusFour.get(Calendar.MONTH),1,0,0,0 );
//        currentMinusFour.set(Calendar.DAY_OF_MONTH, currentMinusFour.getActualMaximum(Calendar.DAY_OF_MONTH));
//        currentMinusFour.set(Calendar.HOUR_OF_DAY, currentMinusFour.getActualMaximum(Calendar.HOUR_OF_DAY));
//        currentMinusFour.set(Calendar.MINUTE, currentMinusFour.getActualMaximum(Calendar.MINUTE));
//        currentMinusFour.set(Calendar.SECOND, currentMinusFour.getActualMaximum(Calendar.SECOND));
//        currentMinusFour.set(Calendar.MILLISECOND,currentMinusFour.getActualMaximum(Calendar.MILLISECOND));
        return currentMinusFour.getTime();
    }
    public static void mergeIntoQuarterlyCollection(String collectionName, Date iteratingDate, Date currentMinus120Date){
        int month = iteratingDate.getMonth();
        int quarter = month/3;
        Calendar getDate = Calendar.getInstance();
        getDate.setTime(iteratingDate);
        Date quarterStartDate = null, quarterEndDate = null;

        getDate.set(getDate.get(Calendar.YEAR), quarter*3, 1,0,0,0);
        quarterStartDate = getDate.getTime();
        getDate.set(Calendar.MONTH,quarter*3+2);
        getDate.set(getDate.get(Calendar.YEAR), getDate.get(Calendar.MONTH), getDate.getActualMaximum(Calendar.DAY_OF_MONTH),23,59,59);
        quarterEndDate = getDate.getTime();

        if (quarterEndDate.after(currentMinus120Date)) {
            quarterEndDate = currentMinus120Date;
            if (quarterStartDate.after(quarterEndDate)) {
                return;
            }
        }

        String enterpID = collectionName.substring(6);


        AggregateIterable<Document> matchValue = mongoTemplate.getCollection(collectionName)
                .aggregate(Arrays.asList(new Document("$match",
                        new Document(reqReceivedTime,
                                new Document("$gte", quarterStartDate)
                                        .append("$lte", quarterEndDate)))));

        if (matchValue.first() != null) {
            String quarterOneCollection = "wa_" + "enterpId_" + quarter + "_" + getDate.get(Calendar.YEAR);
            mongoTemplate.getCollection(collectionName)
                    .aggregate(Arrays.asList(new Document("$match",
                                    new Document(reqReceivedTime,
                                            new Document("$gte", quarterStartDate)
                                                    .append("$lte", quarterEndDate))),
                            new Document("$merge",
                                    new Document("into", quarterOneCollection)))).first();

            // also add delete document code here
        }
    }

    public static MongoIterable<String> collectionsFromDatabase(){
        MongoDatabase db = mongoTemplate.getDb();
        MongoIterable<String> collectionNames = db.listCollectionNames();
        return collectionNames;
    }
}
