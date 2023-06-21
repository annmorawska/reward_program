package com.app.rewardprogram.model.dto;

import java.time.LocalDateTime;

public record NewCustomerDto (String name, LocalDateTime creationDate) {
}
