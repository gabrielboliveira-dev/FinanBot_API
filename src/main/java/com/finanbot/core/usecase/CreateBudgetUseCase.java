package com.finanbot.core.usecase;

import com.finanbot.core.domain.model.Budget;
import com.finanbot.core.usecase.port.BudgetRepository;
import com.finanbot.core.usecase.port.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateBudgetUseCase {

    private final BudgetRepository budgetRepository;
    private final UserRepository userRepository;

    public CreateBudgetUseCase(BudgetRepository budgetRepository, UserRepository userRepository) {
        this.budgetRepository = budgetRepository;
        this.userRepository = userRepository;
    }

    public Budget execute(UUID userId, UUID categoryId, BigDecimal amount, String period, LocalDateTime startDate, LocalDateTime endDate) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new IllegalArgumentException("User not found.");
        }

        Budget budget = new Budget(userId, categoryId, amount, period, startDate, endDate);
        return budgetRepository.save(budget);
    }
}