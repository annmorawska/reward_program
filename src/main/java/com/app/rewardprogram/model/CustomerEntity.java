package com.app.rewardprogram.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "customer")
public class CustomerEntity extends AuditData {

    @Id
    @GeneratedValue
    private ObjectId id;

    private String name;

    private int points;
}
