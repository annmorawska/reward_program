package com.app.rewardprogram.model.dto;

import lombok.Builder;

@Builder
public record MonthlyRewardDto(int year, int month, int points) {
}
