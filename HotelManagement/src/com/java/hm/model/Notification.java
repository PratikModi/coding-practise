package com.java.hm.model;

import java.time.LocalDateTime;

public abstract class Notification {
    private LocalDateTime createdAt;
    private String content;
    private Long notificationId;

    public abstract boolean sendNotification();

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(Long notificationId) {
        this.notificationId = notificationId;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "createdAt=" + createdAt +
                ", content='" + content + '\'' +
                ", notificationId=" + notificationId +
                '}';
    }
}
