package com.recommendation.data_consumption.action;

import com.recommendation.data_consumption.dto.FollowKafkaMessage;
import com.recommendation.data_consumption.dto.UpdateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class FollowListener {

    @Autowired
    KafkaTemplate<String,UpdateMessage> updateKafkaTemplate;

    @KafkaListener(topics = "FOLLOW",containerGroup="group_follow", containerFactory = "followKafkaListenerFactory")
    public void processFollowMessage(FollowKafkaMessage followKafkaMessage)
    {
        UpdateMessage updateMessage=new UpdateMessage();
        updateMessage.setUpdateUnit("POINTS");
        updateMessage.setUpdateValue(0.2);
        updateMessage.setRowId(followKafkaMessage.getUserId());
        updateMessage.setColumnId(followKafkaMessage.getUserIdForFollowed());
        updateMessage.setTarget("USER");

        updateKafkaTemplate.send("UPDATE",updateMessage);


    }
}
