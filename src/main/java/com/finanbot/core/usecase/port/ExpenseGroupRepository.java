package com.finanbot.core.usecase.port;

import com.finanbot.core.domain.model.ExpenseGroup;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ExpenseGroupRepository {
    ExpenseGroup save(ExpenseGroup group);
    Optional<ExpenseGroup> findById(UUID id);
    List<ExpenseGroup> findByUserId(UUID userId);
}