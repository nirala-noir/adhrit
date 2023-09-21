package com.meta.verse.edith;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

@Slf4j
public class EdithApplication {
//	@Autowired
//	public void setMongoTemplate(MongoTemplate mongoTemplate){
//		EdithApplication.mongoTemplate = mongoTemplate;
//	}

	public static final String collectionIdentifier = "dateRangeCollection";
	public static String reqReceivedTime ="reqReceivedTime";
	private static String[] quarterName ={"_jan-mar","_apr-jun","_jul-sep","_oct-dec"};


	public void mover(MongoTemplate mongoTemplate) {
//		SpringApplication.run(EdithApplication.class, args);
		log.info("started application");
		try {
			for (String collectionName : collectionsFromDatabase(mongoTemplate)) {
				if (collectionName.indexOf(collectionIdentifier) == 0 && mongoTemplate.getCollection(collectionName).estimatedDocumentCount() > 0) {

					Date minDateInCollection = minDateFromCollection(collectionName,mongoTemplate).getTime();
					Date currentMinus120Date = currentMinus120Date();

					while(minDateInCollection.before(currentMinus120Date)){
						mergeIntoQuarterlyCollection(collectionName,minDateInCollection,currentMinus120Date,mongoTemplate);
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

		calendar.add(Calendar.MONTH,3);
		return calendar.getTime();

	}

	private static Calendar minDateFromCollection(String collectionName,MongoTemplate mongoTemplate) {
		Calendar calendar = Calendar.getInstance();
		AggregateIterable<Document> aggregateYear = mongoTemplate
				.getCollection(collectionName).aggregate(Arrays.asList(new Document("$match",
						new Document(reqReceivedTime,
								new Document("$exists", true))),
						new Document("$group",
						new Document("_id", "_id")
								.append("minDate",
										new Document("$min", "$reqReceivedTime")))));

		Date minDate = (Date) aggregateYear.first().get("minDate");
		calendar.setTime(minDate);
		return calendar;
	}
	public static Date currentMinus120Date(){
		Calendar currentMinusFour = Calendar.getInstance();
		currentMinusFour.setTime(new Date());
		currentMinusFour.add(Calendar.MONTH,-3);
		currentMinusFour.set(currentMinusFour.get(Calendar.YEAR),currentMinusFour.get(Calendar.MONTH),1,0,0,0);
		return currentMinusFour.getTime();
	}
	public static void mergeIntoQuarterlyCollection(String collectionName, Date iteratingDate, Date currentMinus120Date,MongoTemplate mongoTemplate){
		int month = iteratingDate.getMonth();
		int quarter = month/3;
		Calendar getDate = Calendar.getInstance();
		getDate.setTime(iteratingDate);
		Date quarterStartDate = null, quarterEndDate = null;

		getDate.set(getDate.get(Calendar.YEAR), quarter*3, 1,00,00,00);
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
//		String splitBy="_";
//		String[] names = collectionName.split(splitBy);
//		String enterpID= names[2];

		AggregateIterable<Document> matchValue = mongoTemplate.getCollection(collectionName)
				.aggregate(Arrays.asList(new Document("$match",
						new Document(reqReceivedTime,
								new Document("$exists", true))
								.append("reqReceivedTime",
										new Document("$gte",
												quarterStartDate)
												.append("$lte",
														quarterEndDate)))));

		if (matchValue.first() != null) {
			String quarterOneCollection = "wa_" + "enterpID" + quarterName[quarter] + "_" + getDate.get(Calendar.YEAR);
			mongoTemplate.getCollection(collectionName)
					.aggregate(Arrays.asList(new Document("$match",
									new Document(reqReceivedTime,
											new Document("$exists", true))
											.append("reqReceivedTime",
													new Document("$gte",
															quarterStartDate)
															.append("$lte",
																	quarterEndDate))),
							new Document("$merge",
									new Document("into", quarterOneCollection)))).first();

			// also add delete document code here
			Bson query = new Document(reqReceivedTime, new Document("$exists", true)).append(reqReceivedTime,
									new Document("$gte",
											quarterStartDate)
											.append("$lte",
													quarterEndDate));
			mongoTemplate.getCollection(collectionName).deleteMany(query);
		}
	}

	public static MongoIterable<String> collectionsFromDatabase(MongoTemplate mongoTemplate){
		MongoDatabase db = mongoTemplate.getDb();
		MongoIterable<String> collectionNames = db.listCollectionNames();
		return collectionNames;
	}
}
