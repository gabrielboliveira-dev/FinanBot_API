package com.finanbot.infra.persistence.mapper;

import com.finanbot.core.domain.model.Transaction;
import com.finanbot.infra.persistence.entity.TransactionEntity;
import org.springframework.stereotype.Component;

@Component
public class TransactionMapper {

    public TransactionEntity toEntity(Transaction transaction) {
        return new TransactionEntity(
                transaction.getId(),
                transaction.getUserId(),
                transaction.getAccountId(),
                transaction.getCategoryId(),
                transaction.getAmount(),
                transaction.getType(),
                transaction.getDate(),
                transaction.getDescription()
        );
    }

    public Transaction toDomain(TransactionEntity entity) {
        return new Transaction(
                entity.getId(),
                entity.getUserId(),
                entity.getAccountId(),
                entity.getCategoryId(),
                entity.getAmount(),
                entity.getType(),
                entity.getDate(),
                entity.getDescription()
        );
    }
}