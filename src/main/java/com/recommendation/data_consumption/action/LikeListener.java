package com.recommendation.data_consumption.action;

import com.recommendation.data_consumption.dto.LikeKafkaMessage;
import com.recommendation.data_consumption.service.CategoryCorrelationService;
import com.recommendation.data_consumption.service.UserCorrelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class LikeListener {
    @Autowired
    CategoryCorrelationService categoryCorrelationService;

    @Autowired
    UserCorrelationService userCorrelationService;

    @KafkaListener(topics = "LIKE_EXP",containerGroup="group_like_EXP",containerFactory = "likeKafkaListenerFactory")
    public void processLikeMessage(LikeKafkaMessage likeKafkaMessage)
    {
        String userId=likeKafkaMessage.getUserId();
        String categoryId=likeKafkaMessage.getCategory();

        if(userId!=null&&categoryId!=null)
        {
            categoryCorrelationService.addValue(userId,categoryId,1);
        }

        userId=likeKafkaMessage.getUserId();
        String otherUserId=likeKafkaMessage.getUserIdAuthor();

        if(userId!=null&&otherUserId!=null)
        {
            userCorrelationService.addValue(userId,otherUserId,1);
        }

    }
}
