package com.finanbot.infra.persistence;

import com.finanbot.core.domain.model.Transaction;
import com.finanbot.core.usecase.port.TransactionRepository;
import com.finanbot.infra.persistence.entity.TransactionEntity;
import com.finanbot.infra.persistence.mapper.TransactionMapper;
import com.finanbot.infra.persistence.repository.SpringTransactionRepository;
import org.springframework.stereotype.Repository;

@Repository
public class JpaTransactionRepository implements TransactionRepository {

    private final SpringTransactionRepository springTransactionRepository;
    private final TransactionMapper transactionMapper;

    public JpaTransactionRepository(SpringTransactionRepository springTransactionRepository, TransactionMapper transactionMapper) {
        this.springTransactionRepository = springTransactionRepository;
        this.transactionMapper = transactionMapper;
    }

    @Override
    public Transaction save(Transaction transaction) {
        TransactionEntity entity = transactionMapper.toEntity(transaction);
        return transactionMapper.toDomain(springTransactionRepository.save(entity));
    }
}