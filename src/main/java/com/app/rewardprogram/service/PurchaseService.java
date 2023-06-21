package com.app.rewardprogram.service;

import com.app.rewardprogram.model.dto.NewPurchaseDto;
import com.app.rewardprogram.model.dto.PurchaseDto;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public interface PurchaseService {

    int addNewPurchase(NewPurchaseDto newPurchase);

    List<PurchaseDto> getRecentPurchasesFromDate(ObjectId customerId, LocalDateTime fromDate);
}
