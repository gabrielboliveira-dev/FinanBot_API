package com.finanbot.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Budget {
    private final UUID id;
    private final UUID userId;
    private final UUID categoryId;
    private BigDecimal amount;
    private String period; // e.g., "MONTHLY", "WEEKLY"
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Budget(UUID userId, UUID categoryId, BigDecimal amount, String period, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.period = period;
        this.startDate = startDate;
        this.endDate = endDate;
        validate();
    }

    public Budget(UUID id, UUID userId, UUID categoryId, BigDecimal amount, String period, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.userId = userId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.period = period;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validate() {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Budget amount must be greater than zero.");
        }
        if (startDate == null || endDate == null || startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Invalid date range for budget.");
        }
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public UUID getCategoryId() { return categoryId; }
    public BigDecimal getAmount() { return amount; }
    public String getPeriod() { return period; }
    public LocalDateTime getStartDate() { return startDate; }
    public LocalDateTime getEndDate() { return endDate; }
}