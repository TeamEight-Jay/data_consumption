package com.recommendation.data_consumption.action;

import com.recommendation.data_consumption.dto.FollowKafkaMessage;
import com.recommendation.data_consumption.service.UserCorrelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;


@Service
public class FollowListener {

    @Autowired
    UserCorrelationService userCorrelationService;

    @KafkaListener(topics = "FOLLOW_EXP",containerGroup="group_follow_EXP", containerFactory = "followKafkaListenerFactory")
    public void processFollowMessage(FollowKafkaMessage followKafkaMessage)
    {
        String userId=followKafkaMessage.getUserId();
        String otherUserId=followKafkaMessage.getUserIdForFollowed();

        if(userId!=null&&otherUserId!=null)
        {
            userCorrelationService.addValue(userId,otherUserId,5);
        }


    }
}
