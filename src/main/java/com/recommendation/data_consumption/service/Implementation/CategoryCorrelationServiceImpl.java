package com.recommendation.data_consumption.service.Implementation;

import com.recommendation.data_consumption.entity.CategoryMappingEntity;
import com.recommendation.data_consumption.repository.CategoryCorrelationRepository;
import com.recommendation.data_consumption.service.CategoryCorrelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryCorrelationServiceImpl implements CategoryCorrelationService {

    @Autowired
    CategoryCorrelationRepository categoryCorrelationRepository;

    @Override
    public void addValue(String userId, String categoryName,double value) {
        CategoryMappingEntity userRow=categoryCorrelationRepository.findOne(userId);
        if(userRow!=null)
        {
            double currentValue=userRow.getCategories().getOrDefault(categoryName,0.0);
            currentValue+=value;
            userRow.getCategories().put(categoryName,currentValue);
            categoryCorrelationRepository.save(userRow);
        }
    }

    @Override
    public CategoryMappingEntity getUserMapping(String userId) {
        return categoryCorrelationRepository.findOne(userId);
    }
}
