package com.finanbot.core.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

public class Account {
    private final UUID id;
    private final UUID userId;
    private String name;
    private final AccountType type;
    private BigDecimal balance;

    public Account(UUID userId, String name, AccountType type, BigDecimal initialBalance) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.balance = initialBalance != null ? initialBalance : BigDecimal.ZERO;
        validate();
    }

    public Account(UUID id, UUID userId, String name, AccountType type, BigDecimal balance) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.type = type;
        this.balance = balance;
    }

    private void validate() {
        if (this.name == null || this.name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome da conta é obrigatório.");
        }
        if (this.type == null) {
            throw new IllegalArgumentException("Tipo da conta é obrigatório.");
        }
    }

    public void deposit(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor do depósito deve ser positivo.");
        }
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Valor do saque deve ser positivo.");
        }
        this.balance = this.balance.subtract(amount);
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public String getName() { return name; }
    public AccountType getType() { return type; }
    public BigDecimal getBalance() { return balance; }
}