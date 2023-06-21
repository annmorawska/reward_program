package com.app.rewardprogram.model.dto;

import lombok.Builder;

import java.time.LocalDate;

@Builder
public record MonthDto(LocalDate firstDay, LocalDate lastDay) {
}
