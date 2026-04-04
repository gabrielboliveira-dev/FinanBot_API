package com.finanbot.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Goal {
    private final UUID id;
    private final UUID userId;
    private String name;
    private BigDecimal targetAmount;
    private BigDecimal currentAmount;
    private LocalDateTime deadline;
    private final LocalDateTime createdAt;

    public Goal(UUID userId, String name, BigDecimal targetAmount, LocalDateTime deadline) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.name = name;
        this.targetAmount = targetAmount;
        this.currentAmount = BigDecimal.ZERO;
        this.deadline = deadline;
        this.createdAt = LocalDateTime.now();
        validate();
    }

    public Goal(UUID id, UUID userId, String name, BigDecimal targetAmount, BigDecimal currentAmount, LocalDateTime deadline, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.targetAmount = targetAmount;
        this.currentAmount = currentAmount;
        this.deadline = deadline;
        this.createdAt = createdAt;
    }

    private void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Goal name cannot be empty.");
        }
        if (targetAmount == null || targetAmount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Target amount must be greater than zero.");
        }
    }

    public void addProgress(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Amount to add must be positive.");
        }
        this.currentAmount = this.currentAmount.add(amount);
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public String getName() { return name; }
    public BigDecimal getTargetAmount() { return targetAmount; }
    public BigDecimal getCurrentAmount() { return currentAmount; }
    public LocalDateTime getDeadline() { return deadline; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}