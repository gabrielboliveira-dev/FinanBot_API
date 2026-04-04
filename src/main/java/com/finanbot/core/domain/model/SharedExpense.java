package com.finanbot.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class SharedExpense {
    private final UUID id;
    private final UUID groupId;
    private final UUID paidByUserId;
    private String description;
    private BigDecimal amount;
    private LocalDateTime date;

    public SharedExpense(UUID groupId, UUID paidByUserId, String description, BigDecimal amount, LocalDateTime date) {
        this.id = UUID.randomUUID();
        this.groupId = groupId;
        this.paidByUserId = paidByUserId;
        this.description = description;
        this.amount = amount;
        this.date = date;
        validate();
    }

    public SharedExpense(UUID id, UUID groupId, UUID paidByUserId, String description, BigDecimal amount, LocalDateTime date) {
        this.id = id;
        this.groupId = groupId;
        this.paidByUserId = paidByUserId;
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    private void validate() {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Shared expense amount must be positive.");
        }
        if (description == null || description.trim().isEmpty()) {
            throw new IllegalArgumentException("Description cannot be empty.");
        }
    }

    public UUID getId() { return id; }
    public UUID getGroupId() { return groupId; }
    public UUID getPaidByUserId() { return paidByUserId; }
    public String getDescription() { return description; }
    public BigDecimal getAmount() { return amount; }
    public LocalDateTime getDate() { return date; }
}