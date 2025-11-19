package com.finanbot.core.usecase.dto;

public record CreateUserRequest(
        String name,
        String email,
        String cpf,
        String password
) {}