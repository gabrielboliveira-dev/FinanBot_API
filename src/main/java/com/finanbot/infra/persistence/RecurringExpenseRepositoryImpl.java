package com.finanbot.infra.persistence;

import com.finanbot.core.domain.model.RecurringExpense;
import com.finanbot.core.usecase.port.RecurringExpenseRepository;
import com.finanbot.infra.persistence.entity.RecurringExpenseEntity;
import com.finanbot.infra.persistence.mapper.RecurringExpenseMapper;
import com.finanbot.infra.persistence.repository.JpaRecurringExpenseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class RecurringExpenseRepositoryImpl implements RecurringExpenseRepository {

    private final JpaRecurringExpenseRepository jpaRecurringExpenseRepository;
    private final RecurringExpenseMapper recurringExpenseMapper;

    public RecurringExpenseRepositoryImpl(JpaRecurringExpenseRepository jpaRecurringExpenseRepository, RecurringExpenseMapper recurringExpenseMapper) {
        this.jpaRecurringExpenseRepository = jpaRecurringExpenseRepository;
        this.recurringExpenseMapper = recurringExpenseMapper;
    }

    @Override
    public RecurringExpense save(RecurringExpense recurringExpense) {
        RecurringExpenseEntity entity = recurringExpenseMapper.toEntity(recurringExpense);
        RecurringExpenseEntity savedEntity = jpaRecurringExpenseRepository.save(entity);
        return recurringExpenseMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<RecurringExpense> findById(UUID id) {
        return jpaRecurringExpenseRepository.findById(id).map(recurringExpenseMapper::toDomain);
    }

    @Override
    public List<RecurringExpense> findByUserId(UUID userId) {
        return jpaRecurringExpenseRepository.findByUserId(userId).stream()
                .map(recurringExpenseMapper::toDomain)
                .collect(Collectors.toList());
    }
}