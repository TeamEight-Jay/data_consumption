package com.recommendation.data_consumption.dto;

public class LikeKafkaMessage {
    private String userId;
    private String userIdAuthor;
    private String category;
    private long timestamp;

    @Override
    public String toString() {
        return "LikeKafkaMessage{" +
                "userId='" + userId + '\'' +
                ", userIdAuthor='" + userIdAuthor + '\'' +
                ", category='" + category + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIdAuthor() {
        return userIdAuthor;
    }

    public void setUserIdAuthor(String userIdAuthor) {
        this.userIdAuthor = userIdAuthor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
