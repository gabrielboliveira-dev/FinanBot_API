package com.finanbot.infra.persistence;

import com.finanbot.core.domain.model.ExpenseGroup;
import com.finanbot.core.usecase.port.ExpenseGroupRepository;
import com.finanbot.infra.persistence.entity.ExpenseGroupEntity;
import com.finanbot.infra.persistence.mapper.ExpenseGroupMapper;
import com.finanbot.infra.persistence.repository.JpaExpenseGroupRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ExpenseGroupRepositoryImpl implements ExpenseGroupRepository {

    private final JpaExpenseGroupRepository jpaExpenseGroupRepository;
    private final ExpenseGroupMapper expenseGroupMapper;

    public ExpenseGroupRepositoryImpl(JpaExpenseGroupRepository jpaExpenseGroupRepository, ExpenseGroupMapper expenseGroupMapper) {
        this.jpaExpenseGroupRepository = jpaExpenseGroupRepository;
        this.expenseGroupMapper = expenseGroupMapper;
    }

    @Override
    public ExpenseGroup save(ExpenseGroup expenseGroup) {
        ExpenseGroupEntity entity = expenseGroupMapper.toEntity(expenseGroup);
        ExpenseGroupEntity savedEntity = jpaExpenseGroupRepository.save(entity);
        return expenseGroupMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<ExpenseGroup> findById(UUID id) {
        return jpaExpenseGroupRepository.findById(id).map(expenseGroupMapper::toDomain);
    }

    @Override
    public List<ExpenseGroup> findByUserId(UUID userId) {
        return jpaExpenseGroupRepository.findByCreatedById(userId).stream()
                .map(expenseGroupMapper::toDomain)
                .collect(Collectors.toList());
    }
}