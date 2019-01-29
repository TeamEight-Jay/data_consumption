package com.recommendation.data_consumption.action;

import com.recommendation.data_consumption.dto.FollowKafkaMessage;
import com.recommendation.data_consumption.dto.SubscribeContestKafkaMessage;
import com.recommendation.data_consumption.dto.UpdateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;

public class FollowListener {

    @Autowired
    KafkaTemplate<String,UpdateMessage> updateKafkaTemplate;

    @KafkaListener(topics = "FOLLOW", containerFactory = "followKafkaListenerFactory")
    public void processSubscribeMessage(FollowKafkaMessage followKafkaMessage)
    {
        UpdateMessage updateMessage=new UpdateMessage();
        updateMessage.setUpdateUnit("POINTS");
        updateMessage.setUpdateValue(1);
        updateMessage.setRowId(followKafkaMessage.getUserId());
        updateMessage.setColumnId(followKafkaMessage.getUserIdForFollowed());
        updateMessage.setTarget("SURGE");

        updateKafkaTemplate.send("UPDATE",updateMessage);

    }
}
