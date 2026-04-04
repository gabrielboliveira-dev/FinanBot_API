package com.finanbot.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CashFlowPrediction {
    private final UUID id;
    private final UUID userId;
    private final LocalDate targetDate;
    private final BigDecimal predictedBalance;

    public CashFlowPrediction(UUID userId, LocalDate targetDate, BigDecimal predictedBalance) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.targetDate = targetDate;
        this.predictedBalance = predictedBalance;
        validate();
    }

    public CashFlowPrediction(UUID id, UUID userId, LocalDate targetDate, BigDecimal predictedBalance) {
        this.id = id;
        this.userId = userId;
        this.targetDate = targetDate;
        this.predictedBalance = predictedBalance;
    }

    private void validate() {
        if (targetDate == null) {
            throw new IllegalArgumentException("Target date cannot be null.");
        }
        if (predictedBalance == null) {
            throw new IllegalArgumentException("Predicted balance cannot be null.");
        }
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public LocalDate getTargetDate() { return targetDate; }
    public BigDecimal getPredictedBalance() { return predictedBalance; }
}