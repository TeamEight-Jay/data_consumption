package com.recommendation.data_consumption.dto;

import com.recommendation.data_consumption.entity.CategoryMappingEntity;

public class UpdateCategoryMessage {
    private long timestamp;
    private String target;
    private CategoryMappingEntity content;

    public UpdateCategoryMessage(String target, CategoryMappingEntity content) {
        this.target = target;
        this.content = content;
        this.timestamp=System.currentTimeMillis();
    }

    public UpdateCategoryMessage() {
        this.timestamp=System.currentTimeMillis();
    }


    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public CategoryMappingEntity getContent() {
        return content;
    }

    public void setContent(CategoryMappingEntity content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "UpdateMessage{" +
                "timestamp=" + timestamp +
                ", target='" + target + '\'' +
                ", content=" + content +
                '}';
    }
}
