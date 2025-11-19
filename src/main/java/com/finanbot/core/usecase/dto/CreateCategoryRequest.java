package com.finanbot.core.usecase.dto;

import com.finanbot.core.domain.model.TransactionType;
import java.util.UUID;

public record CreateCategoryRequest(
        UUID userId,
        String name,
        TransactionType type
) {}