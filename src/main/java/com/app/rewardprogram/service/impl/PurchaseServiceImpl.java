package com.app.rewardprogram.service.impl;

import com.app.rewardprogram.model.PurchaseEntity;
import com.app.rewardprogram.model.dto.NewPurchaseDto;
import com.app.rewardprogram.model.dto.PurchaseDto;
import com.app.rewardprogram.repository.PurchaseRepository;
import com.app.rewardprogram.service.PurchaseService;
import com.app.rewardprogram.service.calculator.RewardPointsCalculator;
import com.app.rewardprogram.service.impl.mapper.PurchaseMapper;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final PurchaseMapper purchaseMapper;

    @Override
    public int addNewPurchase(NewPurchaseDto newPurchase) {
        PurchaseEntity purchase = new PurchaseEntity();
        purchase.setCustomerId(newPurchase.customerId());
        purchase.setAmount(newPurchase.amount());
        purchase.setPoints(RewardPointsCalculator.calculatePoints(newPurchase.amount()));
        purchase.setCreationDate(newPurchase.creationDate());
        purchase.setAuditCreationDate(newPurchase.creationDate());
        purchase.setAuditModificationDate(newPurchase.creationDate());
        purchase.setAuditModificationProcess("addNewPurchase");

        return purchaseRepository.save(purchase).getPoints();
    }

    @Override
    public List<PurchaseDto> getRecentPurchasesFromDate(ObjectId customerId, LocalDateTime fromDate) {
        return purchaseRepository.findRecentPurchasesByCustomerId(customerId, fromDate).stream()
                .map(purchase -> purchaseMapper.mapToPurchaseDto(purchase))
                .collect(Collectors.toList());
    }
}
