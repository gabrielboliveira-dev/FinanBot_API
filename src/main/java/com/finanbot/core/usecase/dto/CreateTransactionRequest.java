package com.finanbot.core.usecase.dto;

import com.finanbot.core.domain.model.TransactionType;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateTransactionRequest(
        UUID userId,
        UUID accountId,
        UUID categoryId,
        BigDecimal amount,
        TransactionType type,
        String description,
        LocalDateTime date
) {}