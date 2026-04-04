package com.finanbot.core.usecase.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateBudgetRequest(
        UUID userId,
        UUID categoryId,
        BigDecimal amount,
        String period,
        LocalDateTime startDate,
        LocalDateTime endDate
) {}