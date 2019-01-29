package com.recommendation.data_consumption.state;

import com.recommendation.data_consumption.dto.LikeKafkaMessage;
import com.recommendation.data_consumption.dto.SubscribeContestKafkaMessage;
import com.recommendation.data_consumption.dto.UpdateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

public class LikeListener {
    @Autowired
    KafkaTemplate<String,UpdateMessage> updateKafkaTemplate;

    @KafkaListener(topics = "LIKE", containerFactory = "likeKafkaListenerFactory")
    public void processSubscribeMessage(LikeKafkaMessage likeKafkaMessage)
    {
        UpdateMessage updateMessage=new UpdateMessage();
        updateMessage.setUpdateUnit("POINTS");
        updateMessage.setUpdateValue(1);
        updateMessage.setRowId(likeKafkaMessage.getUserId());
        updateMessage.setColumnId(likeKafkaMessage.getCategory());
        updateMessage.setTarget("SURGE");

        updateKafkaTemplate.send("UPDATE",updateMessage);

    }
}
