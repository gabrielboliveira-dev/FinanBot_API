package com.finanbot.core.domain.model;

import java.util.UUID;

public class Category {
    private final UUID id;
    private final UUID userId;
    private String name;
    private TransactionType type;

    public Category(UUID userId, String name, TransactionType type) {
        this.id = UUID.randomUUID();
        this.userId = userId;
        this.name = name;
        this.type = type;
        validate();
    }

    public Category(UUID id, UUID userId, String name, TransactionType type) {
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.type = type;
    }

    private void validate() {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("Nome inválido");
        if (type == null) throw new IllegalArgumentException("Tipo inválido");
    }

    public UUID getId() { return id; }
    public UUID getUserId() { return userId; }
    public String getName() { return name; }
    public TransactionType getType() { return type; }
}