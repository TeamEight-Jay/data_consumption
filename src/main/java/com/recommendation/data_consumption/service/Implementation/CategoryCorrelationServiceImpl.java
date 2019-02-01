package com.recommendation.data_consumption.service.Implementation;

import com.recommendation.data_consumption.entity.CategoryMappingEntity;
import com.recommendation.data_consumption.repository.CategoryCorrelationRepository;
import com.recommendation.data_consumption.service.CategoryCorrelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
        }
        else
        {
            userRow=new CategoryMappingEntity();
            userRow.setUserId(userId);
            userRow.setCategories(new HashMap<String, Double>());
            userRow.getCategories().put(categoryName,value);
        }
        categoryCorrelationRepository.save(userRow);
    }

    @Override
    public CategoryMappingEntity getUserMapping(String userId) {
        return categoryCorrelationRepository.findOne(userId);
    }

    @Override
    public List<CategoryMappingEntity> getAll() {
        return categoryCorrelationRepository.findAll();
    }

    @Override
    public void deleteAll() {
        categoryCorrelationRepository.deleteAll();
    }
}
