package com.finanbot.core.usecase.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record CreateCashFlowPredictionRequest(
        UUID userId,
        LocalDate targetDate,
        BigDecimal predictedBalance
) {}