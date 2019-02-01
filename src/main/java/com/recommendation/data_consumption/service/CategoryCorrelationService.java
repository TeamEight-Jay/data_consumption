package com.recommendation.data_consumption.service;

import com.recommendation.data_consumption.entity.CategoryMappingEntity;

import java.util.List;

public interface CategoryCorrelationService {
    void addValue(String userId,String categoryName,double value);
    CategoryMappingEntity getUserMapping(String userId);
    List<CategoryMappingEntity> getAll();
    void deleteAll();
}
