package com.finanbot.core.usecase.dto;

public record LoginResponse(String token, String type, String expiresIn) {
}