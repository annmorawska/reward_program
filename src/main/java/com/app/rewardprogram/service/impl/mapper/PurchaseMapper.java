package com.app.rewardprogram.service.impl.mapper;

import com.app.rewardprogram.model.PurchaseEntity;
import com.app.rewardprogram.model.dto.PurchaseDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PurchaseMapper {

    PurchaseDto mapToPurchaseDto(PurchaseEntity purchase);
}
