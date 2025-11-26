package com.finanbot.core.usecase.port;

public interface ChatGateway {
    void sendMessage(String chatId, String message);
}