package com.recommendation.data_consumption.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;

@Document(collection = "UserMapping")
public class UserMappingEntity {

    @Id
    private String userId;

    private HashMap<String,Double> mappedUsers;

    public UserMappingEntity() {
        mappedUsers= new HashMap<String, Double>();
    }

    public UserMappingEntity(String userId) {
        this.userId = userId;
        mappedUsers= new HashMap<String, Double>();
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public HashMap<String, Double> getMappedUsers() {
        return mappedUsers;
    }

    public void setMappedUsers(HashMap<String, Double> mappedUsers) {
        this.mappedUsers = mappedUsers;
    }

    @Override
    public String toString() {
        return "UserMappingEntity{" +
                "userId='" + userId + '\'' +
                ", mappedUsers=" + mappedUsers +
                '}';
    }
}
