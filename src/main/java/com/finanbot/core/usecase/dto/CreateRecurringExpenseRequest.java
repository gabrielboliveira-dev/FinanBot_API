package com.finanbot.core.usecase.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateRecurringExpenseRequest(
        UUID userId,
        UUID categoryId,
        String description,
        BigDecimal amount,
        String frequency,
        LocalDateTime nextDueDate
) {}