package com.finanbot.core.usecase;

import com.finanbot.core.domain.model.SharedExpense;
import com.finanbot.core.usecase.port.ExpenseGroupRepository;
import com.finanbot.core.usecase.port.SharedExpenseRepository;
import com.finanbot.core.usecase.port.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateSharedExpenseUseCase {

    private final SharedExpenseRepository sharedExpenseRepository;
    private final ExpenseGroupRepository expenseGroupRepository;
    private final UserRepository userRepository;

    public CreateSharedExpenseUseCase(SharedExpenseRepository sharedExpenseRepository, ExpenseGroupRepository expenseGroupRepository, UserRepository userRepository) {
        this.sharedExpenseRepository = sharedExpenseRepository;
        this.expenseGroupRepository = expenseGroupRepository;
        this.userRepository = userRepository;
    }

    public SharedExpense execute(UUID groupId, UUID paidByUserId, String description, BigDecimal amount, LocalDateTime date) {
        if (expenseGroupRepository.findById(groupId).isEmpty()) {
            throw new IllegalArgumentException("Expense group not found.");
        }

        if (userRepository.findById(paidByUserId).isEmpty()) {
            throw new IllegalArgumentException("User not found.");
        }

        SharedExpense expense = new SharedExpense(groupId, paidByUserId, description, amount, date);
        return sharedExpenseRepository.save(expense);
    }
}