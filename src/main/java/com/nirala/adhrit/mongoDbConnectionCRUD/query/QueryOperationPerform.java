//package com.meta.verse.mongoDbConnectionCRUD.query;
//
//import com.meta.verse.mongoDbConnectionCRUD.model.Book;
//import com.meta.verse.mongoDbConnectionCRUD.repository.BookRepo;
//import com.mongodb.BasicDBObject;
//import com.mongodb.client.*;
//import org.bson.Document;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.util.*;
//import java.util.concurrent.LinkedTransferQueue;
//
//public class QueryOperationPerform {
//    //sample_supplies
//    //.sales
//    MongoClient mongoClient = MongoClients.create("mongodb+srv://raman_nirala:xjIjKZVYwtUiVkJA@cluster0.vu7ww3s.mongodb.net/BookStore");
//    //        List<Document> databases = mongoClient.listDatabases().into(new ArrayList<>());
////        databases.forEach(db -> System.out.println(db.toJson()));
//    @Autowired
//    BasicDBObject searchQuery;
//    MongoDatabase mongoDatabase = mongoClient.getDatabase("BookStore");
//    MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("Book");
//    FindIterable<Document> findIterable = mongoCollection.find();
//
////    public void performAggregate(){
////        List<Document> list = mongoCollection.aggregate(Arrays.asList(
////                new Document("$match", new Document("storeLocation", "Denver")),
////                new Document("$project", new Document("purchaseMethod", "Online")))).into(new ArrayList<>());
////        list.forEach(System.out::println);
////    }
//
//
//
//    public void fetchAllData() {
//        Iterator itr = findIterable.iterator();
//        while (itr.hasNext()) {
//            System.out.println(itr.next());
//        }
//    }
//
//    public String getBookByIdTemp(Integer id){
//
//        searchQuery.put("id", id);
//        FindIterable<Document> cursor = mongoCollection.find(searchQuery);
//        Iterator itr = findIterable.iterator();
//        return " name";
//    }
//    public String getBookByAuthorName(){
//        searchQuery.put("authorName", "alpha");
//        FindIterable<Document> findIterable = mongoCollection.find(searchQuery);
//        Iterator itr = findIterable.iterator();
//        while (itr.hasNext())
//            System.out.println(itr.next());
//        return "data fetched by authorName";
//    }
//
//}
