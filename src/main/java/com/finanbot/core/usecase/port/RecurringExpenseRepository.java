package com.finanbot.core.usecase.port;

import com.finanbot.core.domain.model.RecurringExpense;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RecurringExpenseRepository {
    RecurringExpense save(RecurringExpense recurringExpense);
    Optional<RecurringExpense> findById(UUID id);
    List<RecurringExpense> findByUserId(UUID userId);
}