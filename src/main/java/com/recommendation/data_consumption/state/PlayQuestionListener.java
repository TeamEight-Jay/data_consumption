package com.recommendation.data_consumption.state;

import com.recommendation.data_consumption.dto.PlayQuestionKafkaMessage;
import com.recommendation.data_consumption.dto.SubscribeContestKafkaMessage;
import com.recommendation.data_consumption.dto.UpdateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class PlayQuestionListener {
    @Autowired
    KafkaTemplate<String,UpdateMessage> updateKafkaTemplate;

    @KafkaListener(topics = "PLAY_QUESTION",containerGroup="group_play", containerFactory = "playQuestionKafkaListenerFactory")
    public void processPlayMessage(PlayQuestionKafkaMessage playQuestionContestKafkaMessage)
    {
        UpdateMessage updateMessage=new UpdateMessage();
        updateMessage.setUpdateUnit("POINT");
        updateMessage.setUpdateValue(0.067);
        updateMessage.setRowId(playQuestionContestKafkaMessage.getUserId());
        updateMessage.setColumnId(playQuestionContestKafkaMessage.getCategory());
        updateMessage.setTarget("CATEGORY");

        updateKafkaTemplate.send("UPDATE",updateMessage);

        updateMessage=new UpdateMessage();
        updateMessage.setUpdateUnit("POINT");
        updateMessage.setUpdateValue(0.05);
        updateMessage.setRowId("trending");
        updateMessage.setColumnId(playQuestionContestKafkaMessage.getCategory());
        updateMessage.setTarget("TRENDING");

        updateKafkaTemplate.send("UPDATE",updateMessage);

    }
}
