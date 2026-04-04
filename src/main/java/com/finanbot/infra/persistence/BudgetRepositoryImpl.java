package com.finanbot.infra.persistence;

import com.finanbot.core.domain.model.Budget;
import com.finanbot.core.usecase.port.BudgetRepository;
import com.finanbot.infra.persistence.entity.BudgetEntity;
import com.finanbot.infra.persistence.mapper.BudgetMapper;
import com.finanbot.infra.persistence.repository.JpaBudgetRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class BudgetRepositoryImpl implements BudgetRepository {

    private final JpaBudgetRepository jpaBudgetRepository;
    private final BudgetMapper budgetMapper;

    public BudgetRepositoryImpl(JpaBudgetRepository jpaBudgetRepository, BudgetMapper budgetMapper) {
        this.jpaBudgetRepository = jpaBudgetRepository;
        this.budgetMapper = budgetMapper;
    }

    @Override
    public Budget save(Budget budget) {
        BudgetEntity entity = budgetMapper.toEntity(budget);
        BudgetEntity savedEntity = jpaBudgetRepository.save(entity);
        return budgetMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Budget> findById(UUID id) {
        return jpaBudgetRepository.findById(id).map(budgetMapper::toDomain);
    }

    @Override
    public List<Budget> findByUserId(UUID userId) {
        return jpaBudgetRepository.findByUserId(userId).stream()
                .map(budgetMapper::toDomain)
                .collect(Collectors.toList());
    }
}