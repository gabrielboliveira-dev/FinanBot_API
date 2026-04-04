package com.finanbot.core.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Alert {
    private final UUID id;
    private final UUID userId;
    private String type; // e.g., "BUDGET_EXCEEDED", "BILL_DUE"
    private String message;
    private LocalDateTime triggerDate;
    private boolean isRead;

    public Alert(UUID userId, String type, String message, LocalDateTime triggerDate) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.type = type;
        this.message = message;
        this.triggerDate = triggerDate;
        this.isRead = false;
        validate();
    }

    public Alert(UUID id, UUID userId, String type, String message, LocalDateTime triggerDate, boolean isRead) {
        this.id = id;
        this.userId = userId;
        this.type = type;
        this.message = message;
        this.triggerDate = triggerDate;
        this.isRead = isRead;
    }

    private void validate() {
        if (message == null || message.trim().isEmpty()) {
            throw new IllegalArgumentException("Alert message cannot be empty.");
        }
    }

    public void markAsRead() {
        this.isRead = true;
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public String getType() { return type; }
    public String getMessage() { return message; }
    public LocalDateTime getTriggerDate() { return triggerDate; }
    public boolean isRead() { return isRead; }
}