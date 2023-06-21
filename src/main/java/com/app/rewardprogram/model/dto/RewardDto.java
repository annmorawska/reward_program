package com.app.rewardprogram.model.dto;

import lombok.Builder;
import org.bson.types.ObjectId;

import java.util.List;

@Builder
public record RewardDto (ObjectId customerId, List<MonthlyRewardDto> lastThreeMonthsReward, int lastThreeMonthsPoints) {
}
