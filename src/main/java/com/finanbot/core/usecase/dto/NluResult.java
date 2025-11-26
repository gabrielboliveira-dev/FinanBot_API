package com.finanbot.core.usecase.dto;

import java.math.BigDecimal;

public record NluResult(
        String intent,
        BigDecimal amount,
        String description,
        String category
) {}