package com.finanbot.core.usecase;

import com.finanbot.core.domain.model.Alert;
import com.finanbot.core.usecase.port.AlertRepository;
import com.finanbot.core.usecase.port.UserRepository;

import java.time.LocalDateTime;
import java.util.UUID;

public class CreateAlertUseCase {

    private final AlertRepository alertRepository;
    private final UserRepository userRepository;

    public CreateAlertUseCase(AlertRepository alertRepository, UserRepository userRepository) {
        this.alertRepository = alertRepository;
        this.userRepository = userRepository;
    }

    public Alert execute(UUID userId, String type, String message, LocalDateTime triggerDate) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new IllegalArgumentException("User not found.");
        }

        Alert alert = new Alert(userId, type, message, triggerDate);
        return alertRepository.save(alert);
    }
}