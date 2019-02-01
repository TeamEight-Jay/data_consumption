package com.recommendation.data_consumption;

import com.recommendation.data_consumption.dto.*;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KakfaConfiguration {

    @Bean
    public ProducerFactory<String, UpdateMessage> producerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);


        return new DefaultKafkaProducerFactory<>(config);
    }


    @Bean
    public KafkaTemplate<String, UpdateMessage> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ConsumerFactory<String, String> consumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_string_EXP");

        return new DefaultKafkaConsumerFactory<>(config);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

    //Subscribe contest consumer
    @Bean
    public ConsumerFactory<String, SubscribeContestKafkaMessage> subscribeContestConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_subscribe_EXP");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(SubscribeContestKafkaMessage.class));
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, SubscribeContestKafkaMessage> subscribeContestKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, SubscribeContestKafkaMessage> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(subscribeContestConsumerFactory());
        return factory;
    }

    //Like consumer
    @Bean
    public ConsumerFactory<String, LikeKafkaMessage> likeConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_like_EXP");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(LikeKafkaMessage.class));
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, LikeKafkaMessage> likeKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, LikeKafkaMessage> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(likeConsumerFactory());
        return factory;
    }

    //Play question consumer
    @Bean
    public ConsumerFactory<String, PlayQuestionKafkaMessage> playQuestionConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_play_EXP");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(PlayQuestionKafkaMessage.class));
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, PlayQuestionKafkaMessage> playQuestionKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, PlayQuestionKafkaMessage> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(playQuestionConsumerFactory());
        return factory;
    }

    //Follow consumer
    @Bean
    public ConsumerFactory<String, FollowKafkaMessage> followConsumerFactory() {
        Map<String, Object> config = new HashMap<>();

        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "group_follow_EXP");

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(),
                new JsonDeserializer<>(FollowKafkaMessage.class));
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, FollowKafkaMessage> followKafkaListenerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, FollowKafkaMessage> factory = new ConcurrentKafkaListenerContainerFactory();
        factory.setConsumerFactory(followConsumerFactory());
        return factory;
    }




}

