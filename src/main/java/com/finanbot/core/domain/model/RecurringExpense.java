package com.finanbot.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class RecurringExpense {
    private final UUID id;
    private final UUID userId;
    private final UUID categoryId;
    private String description;
    private BigDecimal amount;
    private String frequency; // e.g., "MONTHLY", "YEARLY"
    private LocalDateTime nextDueDate;
    private boolean isActive;

    public RecurringExpense(UUID userId, UUID categoryId, String description, BigDecimal amount, String frequency, LocalDateTime nextDueDate) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.categoryId = categoryId;
        this.description = description;
        this.amount = amount;
        this.frequency = frequency;
        this.nextDueDate = nextDueDate;
        this.isActive = true;
        validate();
    }

    public RecurringExpense(UUID id, UUID userId, UUID categoryId, String description, BigDecimal amount, String frequency, LocalDateTime nextDueDate, boolean isActive) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.description = description;
        this.amount = amount;
        this.frequency = frequency;
        this.nextDueDate = nextDueDate;
        this.isActive = isActive;
    }

    private void validate() {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Recurring expense amount must be positive.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
    }

    public void deactivate() {
        this.isActive = false;
    }

    public void updateNextDueDate(LocalDateTime newDate) {
        this.nextDueDate = newDate;
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public UUID getCategoryId() { return categoryId; }
    public String getDescription() { return description; }
    public BigDecimal getAmount() { return amount; }
    public String getFrequency() { return frequency; }
    public LocalDateTime getNextDueDate() { return nextDueDate; }
    public boolean isActive() { return isActive; }
}