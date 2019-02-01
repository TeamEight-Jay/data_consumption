package com.recommendation.data_consumption.state;

import com.recommendation.data_consumption.dto.SubscribeContestKafkaMessage;
import com.recommendation.data_consumption.service.CategoryCorrelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class SubscribeListener {

    @Autowired
    CategoryCorrelationService categoryCorrelationService;


    @KafkaListener(topics = "SUBSCRIBE_EXP",containerGroup="group_subscribe_EXP",containerFactory = "subscribeContestKafkaListenerFactory")
    public void processSubscribeMessage(SubscribeContestKafkaMessage subscribeContestKafkaMessage)
    {
        String userId=subscribeContestKafkaMessage.getUserId();
        String categoryId=subscribeContestKafkaMessage.getCategory();

        if(userId!=null&&categoryId!=null)
        {
            categoryCorrelationService.addValue(userId,categoryId,5);
        }

    }

}
