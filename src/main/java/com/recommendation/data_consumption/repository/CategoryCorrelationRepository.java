package com.recommendation.data_consumption.repository;

import com.recommendation.data_consumption.entity.CategoryMappingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryCorrelationRepository extends MongoRepository<CategoryMappingEntity,String> {
}
