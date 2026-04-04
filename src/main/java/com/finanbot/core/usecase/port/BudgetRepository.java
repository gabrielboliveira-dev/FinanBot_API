package com.finanbot.core.usecase.port;

import com.finanbot.core.domain.model.Budget;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BudgetRepository {
    Budget save(Budget budget);
    Optional<Budget> findById(UUID id);
    List<Budget> findByUserId(UUID userId);
}