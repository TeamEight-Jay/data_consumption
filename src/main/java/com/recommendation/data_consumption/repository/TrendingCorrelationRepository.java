package com.recommendation.data_consumption.repository;

import com.recommendation.data_consumption.entity.TrendMappingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TrendingCorrelationRepository extends MongoRepository<TrendMappingEntity, String> {
}
