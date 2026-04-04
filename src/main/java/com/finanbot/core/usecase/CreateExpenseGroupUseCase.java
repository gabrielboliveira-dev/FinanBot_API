package com.finanbot.core.usecase;

import com.finanbot.core.domain.model.ExpenseGroup;
import com.finanbot.core.usecase.port.ExpenseGroupRepository;
import com.finanbot.core.usecase.port.UserRepository;

import java.util.UUID;

public class CreateExpenseGroupUseCase {

    private final ExpenseGroupRepository expenseGroupRepository;
    private final UserRepository userRepository;

    public CreateExpenseGroupUseCase(ExpenseGroupRepository expenseGroupRepository, UserRepository userRepository) {
        this.expenseGroupRepository = expenseGroupRepository;
        this.userRepository = userRepository;
    }

    public ExpenseGroup execute(String name, UUID createdBy) {
        if (userRepository.findById(createdBy).isEmpty()) {
            throw new IllegalArgumentException("User not found.");
        }

        ExpenseGroup group = new ExpenseGroup(name, createdBy);
        return expenseGroupRepository.save(group);
    }
}