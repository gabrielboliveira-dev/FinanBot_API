package com.finanbot.core.usecase.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateSharedExpenseRequest(
        UUID groupId,
        UUID paidByUserId,
        String description,
        BigDecimal amount,
        LocalDateTime date
) {}