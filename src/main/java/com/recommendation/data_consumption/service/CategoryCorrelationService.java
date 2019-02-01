package com.recommendation.data_consumption.service;

import com.recommendation.data_consumption.entity.CategoryMappingEntity;

public interface CategoryCorrelationService {
    void addValue(String userId,String categoryName,double value);
    CategoryMappingEntity getUserMapping(String userId);
}
