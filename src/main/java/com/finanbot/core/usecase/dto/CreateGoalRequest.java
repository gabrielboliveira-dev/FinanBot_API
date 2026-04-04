package com.finanbot.core.usecase.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateGoalRequest(
        UUID userId,
        String name,
        BigDecimal targetAmount,
        LocalDateTime deadline
) {}