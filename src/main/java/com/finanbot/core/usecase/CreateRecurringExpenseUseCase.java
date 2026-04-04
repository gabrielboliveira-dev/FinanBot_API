package com.finanbot.core.usecase;

import com.finanbot.core.domain.model.RecurringExpense;
import com.finanbot.core.usecase.port.RecurringExpenseRepository;
import com.finanbot.core.usecase.port.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateRecurringExpenseUseCase {

    private final RecurringExpenseRepository recurringExpenseRepository;
    private final UserRepository userRepository;

    public CreateRecurringExpenseUseCase(RecurringExpenseRepository recurringExpenseRepository, UserRepository userRepository) {
        this.recurringExpenseRepository = recurringExpenseRepository;
        this.userRepository = userRepository;
    }

    public RecurringExpense execute(UUID userId, UUID categoryId, String description, BigDecimal amount, String frequency, LocalDateTime nextDueDate) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new IllegalArgumentException("User not found.");
        }

        RecurringExpense recurringExpense = new RecurringExpense(userId, categoryId, description, amount, frequency, nextDueDate);
        return recurringExpenseRepository.save(recurringExpense);
    }
}