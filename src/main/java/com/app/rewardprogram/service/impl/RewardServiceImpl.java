package com.app.rewardprogram.service.impl;

import com.app.rewardprogram.model.dto.MonthDto;
import com.app.rewardprogram.model.dto.MonthlyRewardDto;
import com.app.rewardprogram.model.dto.PurchaseDto;
import com.app.rewardprogram.model.dto.RewardDto;
import com.app.rewardprogram.service.PurchaseService;
import com.app.rewardprogram.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {

    private final PurchaseService purchaseService;
    private final Clock clock;

    private static final int NUMBER_OF_RECENT_MONTHS = 3;

    @Override
    public RewardDto retrieveRewardData(ObjectId customerId) {
        LocalDateTime now = LocalDateTime.now(clock);
        List<MonthDto> lastThreeMonths = retrieveThreeLastMonths(now);
        List<PurchaseDto> recentPurchases = purchaseService.getRecentPurchasesFromDate(customerId, lastThreeMonths.get(lastThreeMonths.size() - 1).lastDay().atTime(LocalTime.MAX));

        int totalPoints = recentPurchases.stream().mapToInt(PurchaseDto::points).sum();
        List<MonthlyRewardDto> monthlyRewards = lastThreeMonths.stream()
                .map(month -> MonthlyRewardDto.builder()
                        .year(month.firstDay().getYear())
                        .month(month.firstDay().getMonthValue())
                        .points(recentPurchases.stream()
                                .filter(purchase -> !purchase.creationDate().isBefore(month.firstDay().atStartOfDay())
                                        && !purchase.creationDate().isAfter(month.lastDay().atTime(LocalTime.MAX)))
                                .mapToInt(PurchaseDto::points)
                                .sum())
                        .build())
                .collect(Collectors.toList());

        return RewardDto.builder()
                .customerId(customerId)
                .lastThreeMonthsReward(monthlyRewards)
                .lastThreeMonthsPoints(totalPoints)
                .build();
    }

    private List<MonthDto> retrieveThreeLastMonths(LocalDateTime localDateTime) {
        YearMonth currentYearMonth = YearMonth.of(localDateTime.getYear(), localDateTime.getMonth());
        List<MonthDto> threeLastMonths = new ArrayList<>();
        for (int i = 0; i < NUMBER_OF_RECENT_MONTHS; i++) {
            YearMonth ym = currentYearMonth.minusMonths(i);
            threeLastMonths.add(MonthDto.builder()
                    .firstDay(LocalDate.of(ym.getYear(), ym.getMonth(), 1))
                    .lastDay(ym.atEndOfMonth())
                    .build());
        }

        return threeLastMonths;
    }
}
