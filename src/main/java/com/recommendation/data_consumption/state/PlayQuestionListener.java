package com.recommendation.data_consumption.state;

import com.recommendation.data_consumption.dto.PlayQuestionKafkaMessage;
import com.recommendation.data_consumption.service.CategoryCorrelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PlayQuestionListener {

    @Autowired
    CategoryCorrelationService categoryCorrelationService;


    @KafkaListener(topics = "PLAY_QUESTION_EXP",containerGroup="group_play_EXP", containerFactory = "playQuestionKafkaListenerFactory")
    public void processPlayMessage(PlayQuestionKafkaMessage playQuestionContestKafkaMessage)
    {
        String userId=playQuestionContestKafkaMessage.getUserId();
        String categoryId=playQuestionContestKafkaMessage.getCategory();

        if(userId!=null&&categoryId!=null)
        {
            categoryCorrelationService.addValue(userId,categoryId,0.34);
        }

    }
}
