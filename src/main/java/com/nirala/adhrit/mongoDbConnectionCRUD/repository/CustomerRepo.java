package com.meta.verse.mongoDbConnectionCRUD.repository;

import com.meta.verse.mongoDbConnectionCRUD.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepo extends MongoRepository<Customer,Integer> {
}
