package com.app.rewardprogram.service;

import com.app.rewardprogram.model.dto.RewardDto;
import org.bson.types.ObjectId;

public interface RewardService {

    RewardDto retrieveRewardData(ObjectId customerId);
}
