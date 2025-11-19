package com.finanbot.core.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaction {
    private final UUID id;
    private final UUID userId;
    private final UUID accountId; // A qual conta pertence
    private final UUID categoryId;
    private final BigDecimal amount;
    private final TransactionType type;
    private final LocalDateTime date;
    private final String description;

    public Transaction(UUID userId, UUID accountId, UUID categoryId, BigDecimal amount, TransactionType type, LocalDateTime date, String description) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.type = type;
        this.date = date != null ? date : LocalDateTime.now();
        this.description = description;
        validate();
    }

    public Transaction(UUID id, UUID userId, UUID accountId, UUID categoryId, BigDecimal amount, TransactionType type, LocalDateTime date, String description) {
        this.id = id;
        this.userId = userId;
        this.accountId = accountId;
        this.categoryId = categoryId;
        this.amount = amount;
        this.type = type;
        this.date = date;
        this.description = description;
    }

    private void validate() {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser maior que zero.");
        }
        if (accountId == null) throw new IllegalArgumentException("Conta é obrigatória.");
        if (categoryId == null) throw new IllegalArgumentException("Categoria é obrigatória.");
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public UUID getAccountId() { return accountId; }
    public UUID getCategoryId() { return categoryId; }
    public BigDecimal getAmount() { return amount; }
    public TransactionType getType() { return type; }
    public LocalDateTime getDate() { return date; }
    public String getDescription() { return description; }
}