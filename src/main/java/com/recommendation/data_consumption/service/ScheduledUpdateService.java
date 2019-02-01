package com.recommendation.data_consumption.service;

import com.recommendation.data_consumption.dto.UpdateCategoryMessage;
import com.recommendation.data_consumption.entity.CategoryMappingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduledUpdateService {

    @Autowired
    CategoryCorrelationService categoryCorrelationService;

    @Autowired
    KafkaTemplate<String,UpdateCategoryMessage> kafkaTemplate;

    @Scheduled(fixedDelay = 10000)
    public void sendCategoryUpdate()
    {
        List<CategoryMappingEntity> categoryMappingEntityList=categoryCorrelationService.getAll();
        for(CategoryMappingEntity categoryMappingEntity:categoryMappingEntityList)
        {
            UpdateCategoryMessage categoryMappingEntityUpdateMessage=new UpdateCategoryMessage();
            categoryMappingEntityUpdateMessage.setTarget("CATEGORY");
            categoryMappingEntityUpdateMessage.setContent(categoryMappingEntity);
            kafkaTemplate.send("UPDATE_CATEGORY",categoryMappingEntityUpdateMessage);
        }
        System.out.println("Flushed "+categoryMappingEntityList.size()+" UPDATES");
        categoryCorrelationService.deleteAll();
    }
}
