package com.finanbot.core.usecase.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record CreateAlertRequest(
        UUID userId,
        String type,
        String message,
        LocalDateTime triggerDate
) {}