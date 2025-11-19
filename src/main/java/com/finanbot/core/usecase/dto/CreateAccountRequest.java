package com.finanbot.core.usecase.dto;

import com.finanbot.core.domain.model.AccountType;
import java.math.BigDecimal;
import java.util.UUID;

public record CreateAccountRequest(
        UUID userId,
        String name,
        AccountType type,
        BigDecimal initialBalance
) {}