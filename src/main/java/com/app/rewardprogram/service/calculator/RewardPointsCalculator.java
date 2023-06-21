package com.app.rewardprogram.service.calculator;

import java.math.BigDecimal;

public class RewardPointsCalculator {

    private static final BigDecimal MINIMUM_AMOUNT = BigDecimal.valueOf(50);

    public static int calculatePoints(BigDecimal amount) {
        if (amount.compareTo(MINIMUM_AMOUNT) <= 0) {
            return 0;
        } else {
            int pointsToCount = amount.subtract(MINIMUM_AMOUNT).intValue();
            return pointsToCount + Math.max(0, pointsToCount - MINIMUM_AMOUNT.intValue());
        }
    }
}
