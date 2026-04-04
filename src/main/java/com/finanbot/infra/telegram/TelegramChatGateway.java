package com.finanbot.infra.telegram;

import com.finanbot.core.usecase.port.ChatGateway;
import org.springframework.stereotype.Component;

@Component
public class TelegramChatGateway implements ChatGateway {

    private final FinanTelegramBot bot;

    public TelegramChatGateway(FinanTelegramBot bot) {
        this.bot = bot;
    }

    @Override
    public void sendMessage(String chatId, String message) {
        bot.sendText(Long.parseLong(chatId), message);
    }
}