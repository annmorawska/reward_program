package com.app.rewardprogram.model.dto;

import lombok.Builder;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;

@Builder
public record PurchaseDto(ObjectId customerId, int points, LocalDateTime creationDate) {
}
