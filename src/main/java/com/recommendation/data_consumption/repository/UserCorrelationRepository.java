package com.recommendation.data_consumption.repository;

import com.recommendation.data_consumption.entity.UserMappingEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserCorrelationRepository extends MongoRepository<UserMappingEntity,String> {
}
