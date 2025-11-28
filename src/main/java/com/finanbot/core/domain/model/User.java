package com.finanbot.core.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {

    private final UUID id;
    private String name;
    private final Email email;
    private final Cpf cpf;
    private String password;
    private final LocalDateTime createdAt;
    private boolean active;

    private String telegramChatId;

    public User(String name, String email, String cpf, String password) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.email = new Email(email);
        this.cpf = new Cpf(cpf);
        this.password = password;
        this.createdAt = LocalDateTime.now();
        this.active = true;
        this.telegramChatId = null;
        validate();
    }

    public User(UUID id, String name, String email, String cpf, String password, LocalDateTime createdAt, boolean active, String telegramChatId) {
        this.id = id;
        this.name = name;
        this.email = new Email(email);
        this.cpf = new Cpf(cpf);
        this.password = password;
        this.createdAt = createdAt;
        this.active = active;
        this.telegramChatId = telegramChatId;
    }

    private void validate() {
        if (this.name == null || this.name.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário não pode ser vazio.");
        }
    }

    public void changePassword(String newPassword) {
        if (newPassword == null || newPassword.length() < 6) {
            throw new IllegalArgumentException("A senha deve ter no mínimo 6 caracteres.");
        }
        this.password = newPassword;
    }

    public void deactivate() {
        this.active = false;
    }

    public void linkTelegramAccount(String chatId) {
        if (chatId == null || chatId.trim().isEmpty()) {
            throw new IllegalArgumentException("ID do Chat inválido.");
        }
        this.telegramChatId = chatId;
    }

    public UUID getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email.getValue(); }
    public String getCpf() { return cpf.getValue(); }
    public String getPassword() { return password; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public boolean isActive() { return active; }
    public String getTelegramChatId() { return telegramChatId; }
}