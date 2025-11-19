package com.finanbot.infra.persistence.entity;

import com.finanbot.core.domain.model.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "categories")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntity {
    @Id
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    private String name;

    @Enumerated(EnumType.STRING)
    private TransactionType type;
}