package com.finanbot.core.usecase;

import com.finanbot.core.domain.model.Goal;
import com.finanbot.core.usecase.port.GoalRepository;
import com.finanbot.core.usecase.port.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateGoalUseCase {

    private final GoalRepository goalRepository;
    private final UserRepository userRepository;

    public CreateGoalUseCase(GoalRepository goalRepository, UserRepository userRepository) {
        this.goalRepository = goalRepository;
        this.userRepository = userRepository;
    }

    public Goal execute(UUID userId, String name, BigDecimal targetAmount, LocalDateTime deadline) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new IllegalArgumentException("User not found.");
        }

        Goal goal = new Goal(userId, name, targetAmount, deadline);
        return goalRepository.save(goal);
    }
}