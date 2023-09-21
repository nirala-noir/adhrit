package com.meta.verse.moip;

//import com.jarvis.edith.EdithApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class Testing {
    @Autowired
	public void setMongoTemplate(MongoTemplate mongoTemplate){
		Testing.mongoTemplate = mongoTemplate;
	}
    static MongoTemplate mongoTemplate;
    public static void main(String[] args) {
        SpringApplication.run(Testing.class,args);
//        EdithApplication edithApplication = new EdithApplication();
//        edithApplication.mover(mongoTemplate);
    }
}
