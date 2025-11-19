package com.finanbot.core.domain.model;

public class Cpf {
    private final String value;

    public Cpf(String value) {
        if (value == null || !isValid(value)) {
            throw new IllegalArgumentException("CPF inválido: " + value);
        }
        this.value = value.replaceAll("\\D", "");
    }

    public String getValue() {
        return value;
    }

    private boolean isValid(String cpf) {
        String sanitized = cpf.replaceAll("\\D", "");
        return sanitized.length() == 11;
    }
}