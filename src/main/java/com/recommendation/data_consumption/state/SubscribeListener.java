package com.recommendation.data_consumption.state;

import com.recommendation.data_consumption.dto.SubscribeContestKafkaMessage;
import com.recommendation.data_consumption.dto.UpdateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class SubscribeListener {

    @Autowired
    KafkaTemplate<String,UpdateMessage> updateKafkaTemplate;

    @KafkaListener(topics = "SUBSCRIBE",containerGroup="group_subscribe",containerFactory = "subscribeContestKafkaListenerFactory")
    public void processSubscribeMessage(SubscribeContestKafkaMessage subscribeContestKafkaMessage)
    {
        UpdateMessage updateMessage=new UpdateMessage();
        updateMessage.setUpdateUnit("POINTS");
        updateMessage.setUpdateValue(5);
        updateMessage.setRowId(subscribeContestKafkaMessage.getUserId());
        updateMessage.setColumnId(subscribeContestKafkaMessage.getCategory());
        updateMessage.setTarget("CATEGORY");
        updateKafkaTemplate.send("UPDATE",updateMessage);

        updateMessage=new UpdateMessage();
        updateMessage.setUpdateUnit("POINTS");
        updateMessage.setUpdateValue(1);
        updateMessage.setRowId("trending");
        updateMessage.setColumnId(subscribeContestKafkaMessage.getCategory());
        updateMessage.setTarget("TRENDING");
        updateKafkaTemplate.send("UPDATE",updateMessage);

    }

}
