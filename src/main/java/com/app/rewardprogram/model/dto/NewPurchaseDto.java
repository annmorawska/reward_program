package com.app.rewardprogram.model.dto;

import org.bson.types.ObjectId;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record NewPurchaseDto (ObjectId customerId, BigDecimal amount, LocalDateTime creationDate) {
}