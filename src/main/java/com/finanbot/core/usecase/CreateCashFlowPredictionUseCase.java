package com.finanbot.core.usecase;

import com.finanbot.core.domain.model.CashFlowPrediction;
import com.finanbot.core.usecase.port.CashFlowPredictionRepository;
import com.finanbot.core.usecase.port.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public class CreateCashFlowPredictionUseCase {

    private final CashFlowPredictionRepository predictionRepository;
    private final UserRepository userRepository;

    public CreateCashFlowPredictionUseCase(CashFlowPredictionRepository predictionRepository, UserRepository userRepository) {
        this.predictionRepository = predictionRepository;
        this.userRepository = userRepository;
    }

    public CashFlowPrediction execute(UUID userId, LocalDate targetDate, BigDecimal predictedBalance) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new IllegalArgumentException("User not found.");
        }

        CashFlowPrediction prediction = new CashFlowPrediction(userId, targetDate, predictedBalance);
        return predictionRepository.save(prediction);
    }
}