package com.app.rewardprogram.repository;

import com.app.rewardprogram.model.CustomerEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<CustomerEntity, ObjectId> {
}
