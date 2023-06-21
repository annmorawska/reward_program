package com.app.rewardprogram.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "purchase")
public class PurchaseEntity extends AuditData {

    @Id
    @GeneratedValue
    private ObjectId id;

    private ObjectId customerId;

    private BigDecimal amount;

    private int points;

    protected LocalDateTime creationDate;
}
