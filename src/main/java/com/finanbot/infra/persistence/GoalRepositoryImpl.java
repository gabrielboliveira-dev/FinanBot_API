package com.finanbot.infra.persistence;

import com.finanbot.core.domain.model.Goal;
import com.finanbot.core.usecase.port.GoalRepository;
import com.finanbot.infra.persistence.entity.GoalEntity;
import com.finanbot.infra.persistence.mapper.GoalMapper;
import com.finanbot.infra.persistence.repository.JpaGoalRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class GoalRepositoryImpl implements GoalRepository {

    private final JpaGoalRepository jpaGoalRepository;
    private final GoalMapper goalMapper;

    public GoalRepositoryImpl(JpaGoalRepository jpaGoalRepository, GoalMapper goalMapper) {
        this.jpaGoalRepository = jpaGoalRepository;
        this.goalMapper = goalMapper;
    }

    @Override
    public Goal save(Goal goal) {
        GoalEntity entity = goalMapper.toEntity(goal);
        GoalEntity savedEntity = jpaGoalRepository.save(entity);
        return goalMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Goal> findById(UUID id) {
        return jpaGoalRepository.findById(id).map(goalMapper::toDomain);
    }

    @Override
    public List<Goal> findByUserId(UUID userId) {
        return jpaGoalRepository.findByUserId(userId).stream()
                .map(goalMapper::toDomain)
                .collect(Collectors.toList());
    }
}