package com.finanbot.infra.persistence;

import com.finanbot.core.domain.model.SharedExpense;
import com.finanbot.core.usecase.port.SharedExpenseRepository;
import com.finanbot.infra.persistence.entity.SharedExpenseEntity;
import com.finanbot.infra.persistence.mapper.SharedExpenseMapper;
import com.finanbot.infra.persistence.repository.JpaSharedExpenseRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class SharedExpenseRepositoryImpl implements SharedExpenseRepository {

    private final JpaSharedExpenseRepository jpaSharedExpenseRepository;
    private final SharedExpenseMapper sharedExpenseMapper;

    public SharedExpenseRepositoryImpl(JpaSharedExpenseRepository jpaSharedExpenseRepository, SharedExpenseMapper sharedExpenseMapper) {
        this.jpaSharedExpenseRepository = jpaSharedExpenseRepository;
        this.sharedExpenseMapper = sharedExpenseMapper;
    }

    @Override
    public SharedExpense save(SharedExpense sharedExpense) {
        SharedExpenseEntity entity = sharedExpenseMapper.toEntity(sharedExpense);
        SharedExpenseEntity savedEntity = jpaSharedExpenseRepository.save(entity);
        return sharedExpenseMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<SharedExpense> findById(UUID id) {
        return jpaSharedExpenseRepository.findById(id).map(sharedExpenseMapper::toDomain);
    }

    @Override
    public List<SharedExpense> findByGroupId(UUID groupId) {
        return jpaSharedExpenseRepository.findByGroupId(groupId).stream()
                .map(sharedExpenseMapper::toDomain)
                .collect(Collectors.toList());
    }
}