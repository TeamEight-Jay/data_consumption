package com.recommendation.data_consumption.dto;

public class FollowKafkaMessage {
    private String userId;
    private String userIdForFollowed;
    private long timestamp;

    @Override
    public String toString() {
        return "FollowKafkaMessage{" +
                "userId='" + userId + '\'' +
                ", userIdForFollowed='" + userIdForFollowed + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserIdForFollowed() {
        return userIdForFollowed;
    }

    public void setUserIdForFollowed(String userIdForFollowed) {
        this.userIdForFollowed = userIdForFollowed;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
