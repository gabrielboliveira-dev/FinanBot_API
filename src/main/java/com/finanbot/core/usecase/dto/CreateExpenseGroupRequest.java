package com.finanbot.core.usecase.dto;

import java.util.UUID;

public record CreateExpenseGroupRequest(
        String name,
        UUID createdBy
) {}