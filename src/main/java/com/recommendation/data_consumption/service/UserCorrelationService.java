package com.recommendation.data_consumption.service;

import com.recommendation.data_consumption.entity.UserMappingEntity;

public interface UserCorrelationService {

    void addValue(String userId,String otherUserId,double value);
    UserMappingEntity getUserMapping(String userId);

}
