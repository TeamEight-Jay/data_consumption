package com.recommendation.data_consumption.action;

import com.recommendation.data_consumption.dto.LikeKafkaMessage;
import com.recommendation.data_consumption.dto.UpdateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class LikeListener {
    @Autowired
    KafkaTemplate<String,UpdateMessage> updateKafkaTemplate;

    @KafkaListener(topics = "LIKE",group="group_like",containerFactory = "likeKafkaListenerFactory")
    public void processLikeMessage(LikeKafkaMessage likeKafkaMessage)
    {
        UpdateMessage updateMessage=new UpdateMessage();
        updateMessage.setUpdateUnit("POINTS");
        updateMessage.setUpdateValue(1);
        updateMessage.setRowId(likeKafkaMessage.getUserId());
        updateMessage.setColumnId(likeKafkaMessage.getCategory());
        updateMessage.setTarget("SURGE");

        updateKafkaTemplate.send("UPDATE",updateMessage);

        updateMessage=new UpdateMessage();
        updateMessage.setUpdateUnit("POINTS");
        updateMessage.setUpdateValue(1);
        updateMessage.setRowId(likeKafkaMessage.getUserId());
        updateMessage.setColumnId(likeKafkaMessage.getUserIdAuthor());
        updateMessage.setTarget("USER");

        updateKafkaTemplate.send("UPDATE",updateMessage);

    }
}
