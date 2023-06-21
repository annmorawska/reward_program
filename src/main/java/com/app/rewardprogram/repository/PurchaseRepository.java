package com.app.rewardprogram.repository;

import com.app.rewardprogram.model.PurchaseEntity;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseRepository extends MongoRepository<PurchaseEntity, ObjectId> {

    @Query("{'customerId': ?0, 'creationDate': {$gte: ?1}}")
    List<PurchaseEntity> findRecentPurchasesByCustomerId(ObjectId customerId, LocalDateTime date);
}
