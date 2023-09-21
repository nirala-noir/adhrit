package com.meta.verse.moveFromMisToQuarterlyCollection;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Component
@SpringBootApplication
public class Clutch {
    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate){
        Clutch.mongoTemplate = mongoTemplate;
    }

    static private MongoTemplate mongoTemplate;
    public static String reqReceivedTime ="reqReceivedTime";
    public static final String collectionIdentifier = "demo";
    
    public static void main(String[] args) {
        SpringApplication.run(Clutch.class, args);
        
        try {
            for (String collectionName : collectionsFromDatabase()) {
                if (collectionName.indexOf(collectionIdentifier) == 0 && mongoTemplate.getCollection(collectionName).estimatedDocumentCount() > 0) {
                    int minYear = minYearFromCollection(collectionName);

                    Calendar currentMinusFourEndDate = Calendar.getInstance();
                    Date quarterStartDate = null, quarterEndDate = null;

                    while (minYear <= 2022) {
                        int quarterToStart = quarterToStart(minYear, collectionName);

                        for (int i = quarterToStart; i < 4; i++) {
                            setManualDate(minYear, i, currentMinusFourEndDate, quarterStartDate, quarterEndDate);

                            if (quarterEndDate.after(currentMinusFourEndDate.getTime())) {
                                quarterEndDate = currentMinusFourEndDate.getTime();
                                if (quarterStartDate.after(quarterEndDate)) {
                                    break;
                                }
                            }
                            //in other case : before normal flow occur
                            mergeIntoQuarterlyCollection(collectionName, i, minYear, quarterStartDate, quarterEndDate);
                        }
                        minYear++;
                    }

                }
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }

    }


    public static MongoIterable<String> collectionsFromDatabase(){
        MongoDatabase db = mongoTemplate.getDb();
        MongoIterable<String> collectionNames = db.listCollectionNames();
        return collectionNames;
    }
    public static int minYearFromCollection(String collectionName){
        Calendar calendar = Calendar.getInstance();
        AggregateIterable<Document> aggregateYear = mongoTemplate.getCollection(collectionName).aggregate(Arrays.asList(new Document("$sort",
                        new Document(reqReceivedTime, 1L)),
                new Document("$limit", 1L),
                new Document("$project",
                        new Document(reqReceivedTime, 1L).append("_id", 0L))));
        Date date = (Date) aggregateYear.first().get(reqReceivedTime);
        calendar.setTime(date);
        int minYear = calendar.get(Calendar.YEAR);
        return minYear;
    }
    public static int quarterToStart(int minYear, String collectionName){
        Calendar startDateOfYear = Calendar.getInstance();
        Calendar endDateOfyear = Calendar.getInstance();
        Calendar minDateInYear = Calendar.getInstance();

        Date startDate,endDate;
        
        startDateOfYear.set(minYear, 0, 1, 0, 0, 0);
        startDate = startDateOfYear.getTime();
        endDateOfyear.set(minYear, 11, 31, 23, 59, 59);
        endDate = endDateOfyear.getTime();

        AggregateIterable<Document> dateForQuarter = mongoTemplate.getCollection(collectionName)
                .aggregate(Arrays.asList(new Document("$match",
                                new Document(reqReceivedTime,
                                        new Document("$gte", startDate)
                                                .append("$lte", endDate))),
                        new Document("$sort",
                                new Document(reqReceivedTime, 1L)),
                        new Document("$limit", 1L),
                        new Document("$project",
                                new Document(reqReceivedTime, 1L).append("_id", 0L))));
        Date minDate = (Date) dateForQuarter.first().get(reqReceivedTime);
        minDateInYear.setTime(minDate);
        int minQuarter = minDateInYear.get(Calendar.MONTH)/3;
        return minQuarter;
    }
    public static void setManualDate(int minYear, int i, Calendar currentMinusFour,Date startDate ,Date endDate){
        Calendar startDateOfQuarter = Calendar.getInstance();
        Calendar endDateOfQuarter = Calendar.getInstance();

        startDateOfQuarter.set(minYear, i*3, 1, 0, 0, 0);
        startDate = startDateOfQuarter.getTime();
        endDateOfQuarter.set(minYear, (i*3)+2, (i%2==0)?31:30, 23, 59, 59);
        endDate = endDateOfQuarter.getTime();

        currentMinusFour.setTime(new Date());
        currentMinusFour.add(Calendar.MONTH,-4);
        currentMinusFour.set(Calendar.DAY_OF_MONTH, currentMinusFour.getActualMaximum(Calendar.DAY_OF_MONTH));
        currentMinusFour.set(Calendar.HOUR_OF_DAY, currentMinusFour.getActualMaximum(Calendar.HOUR_OF_DAY));
        currentMinusFour.set(Calendar.MINUTE, currentMinusFour.getActualMaximum(Calendar.MINUTE));
        currentMinusFour.set(Calendar.SECOND, currentMinusFour.getActualMaximum(Calendar.SECOND));
        currentMinusFour.set(Calendar.MILLISECOND,currentMinusFour.getActualMaximum(Calendar.MILLISECOND));
    }
    public static void mergeIntoQuarterlyCollection(String collectionName, int i, int minYear, Date quarterStartDate, Date quarterEndDate){
        AggregateIterable<Document> matchValue = mongoTemplate.getCollection(collectionName)
                .aggregate(Arrays.asList(new Document("$match",
                        new Document(reqReceivedTime,
                                new Document("$gte", quarterStartDate)
                                        .append("$lte", quarterEndDate)))));
        if (matchValue.first() != null) {
            String quarterOneCollection = "wa_" + "enterpId_" + (i+1) + "_" + minYear;
            mongoTemplate.getCollection(collectionName)
                    .aggregate(Arrays.asList(new Document("$match",
                                    new Document(reqReceivedTime,
                                            new Document("$gte", quarterStartDate)
                                                    .append("$lte", quarterEndDate))),
                            new Document("$merge",
                                    new Document("into", quarterOneCollection)))).first();
        }
    }
    
}
