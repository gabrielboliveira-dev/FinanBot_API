package com.finanbot.core.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExpenseGroup {
    private final UUID id;
    private String name;
    private final UUID createdBy;
    private final LocalDateTime createdAt;
    private final List<UUID> members;

    public ExpenseGroup(String name, UUID createdBy) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.members = new ArrayList<>();
        this.members.add(createdBy); // Creator is automatically a member
        validate();
    }

    public ExpenseGroup(UUID id, String name, UUID createdBy, LocalDateTime createdAt, List<UUID> members) {
        this.id = id;
        this.name = name;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.members = members != null ? new ArrayList<>(members) : new ArrayList<>();
    }

    private void validate() {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Group name cannot be empty.");
        }
    }

    public void addMember(UUID userId) {
        if (!members.contains(userId)) {
            members.add(userId);
        }
    }

    public void removeMember(UUID userId) {
        // Prevent removing the creator? Or allow it? Depends on business rules.
        members.remove(userId);
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public UUID getCreatedBy() { return createdBy; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public List<UUID> getMembers() { return new ArrayList<>(members); }
}