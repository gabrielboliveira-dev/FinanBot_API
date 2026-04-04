package com.finanbot.core.usecase.port;

import com.finanbot.core.domain.model.SharedExpense;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface SharedExpenseRepository {
    SharedExpense save(SharedExpense sharedExpense);
    Optional<SharedExpense> findById(UUID id);
    List<SharedExpense> findByGroupId(UUID groupId);
}