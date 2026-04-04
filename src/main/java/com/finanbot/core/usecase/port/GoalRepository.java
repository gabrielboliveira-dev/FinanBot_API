package com.finanbot.core.usecase.port;

import com.finanbot.core.domain.model.Goal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GoalRepository {
    Goal save(Goal goal);
    Optional<Goal> findById(UUID id);
    List<Goal> findByUserId(UUID userId);
}