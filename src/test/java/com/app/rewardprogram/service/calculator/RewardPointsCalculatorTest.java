package com.app.rewardprogram.service.calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static com.app.rewardprogram.service.calculator.RewardPointsCalculator.calculatePoints;

@SpringBootTest
public class RewardPointsCalculatorTest {

    @Test
    public void shouldAddNewPurchaseWithZeroAmount() {
        int receivedPoints = calculatePoints(BigDecimal.ZERO);

        Assertions.assertEquals(0, receivedPoints);
    }

    @Test
    public void shouldAddNewPurchaseWithLessThan50Amount() {
        int receivedPoints = calculatePoints(BigDecimal.valueOf(49));

        Assertions.assertEquals(0, receivedPoints);
    }

    @Test
    public void shouldAddNewPurchaseWithMinimumAmount51() {
        int receivedPoints = calculatePoints(BigDecimal.valueOf(51));

        Assertions.assertEquals(1, receivedPoints);
    }

    @Test
    public void shouldAddNewPurchaseWithAmountBetween50And100() {
        int receivedPoints = calculatePoints(BigDecimal.valueOf(70));

        Assertions.assertEquals(20, receivedPoints);
    }

    @Test
    public void shouldAddNewPurchaseWithAmountOver100() {
        int receivedPoints = calculatePoints(BigDecimal.valueOf(120));

        Assertions.assertEquals(90, receivedPoints);
    }
}
