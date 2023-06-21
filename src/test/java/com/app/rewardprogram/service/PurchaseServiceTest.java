package com.app.rewardprogram.service;

import com.app.rewardprogram.TestDataProvider;
import com.app.rewardprogram.model.dto.NewPurchaseDto;
import com.app.rewardprogram.model.dto.PurchaseDto;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static com.app.rewardprogram.TestDataProvider.OBJECT_ID;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PurchaseServiceTest {

    @Autowired
    private PurchaseService purchaseService;

    private final static LocalDateTime DATE = LocalDateTime.of(2023, 6, 21, 12, 0, 0);

    @Test
    public void shouldAddNewPurchase() {
        int receivedPoints = purchaseService.addNewPurchase(createPurchaseTestData(BigDecimal.TEN));

        Assertions.assertNotNull(receivedPoints);
    }

    @Test
    public void shouldReturnProperPurchases() {
        purchaseService.addNewPurchase(createPurchaseTestData(OBJECT_ID, Clock.fixed(DATE.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault())));
        purchaseService.addNewPurchase(createPurchaseTestData(OBJECT_ID, Clock.fixed(DATE.plusNanos(1).atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault())));
        purchaseService.addNewPurchase(createPurchaseTestData(OBJECT_ID, Clock.fixed(DATE.minusNanos(1).atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault())));
        purchaseService.addNewPurchase(createPurchaseTestData(new ObjectId("6891d9694fd2e92b5c443d8b"), Clock.fixed(DATE.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault())));

        List<PurchaseDto> recentPurchases = purchaseService.getRecentPurchasesFromDate(OBJECT_ID, DATE);

        Assertions.assertEquals(2, recentPurchases.size());
    }

    private NewPurchaseDto createPurchaseTestData(BigDecimal amount) {
        return new NewPurchaseDto(OBJECT_ID, amount, LocalDateTime.now());
    }

    private NewPurchaseDto createPurchaseTestData(ObjectId id, Clock clock) {
        return new NewPurchaseDto(id, BigDecimal.TEN, LocalDateTime.now(clock));
    }
}
