package com.finanbot.infra.telegram;

import com.finanbot.core.usecase.dto.IncomingChatMessage;
import com.finanbot.core.usecase.port.QueueGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Component
public class FinanTelegramBot extends TelegramLongPollingBot {

    private final QueueGateway queueGateway;

    @Value("${telegram.bot.username}")
    private String botUsername;

    @Value("${telegram.bot.token}")
    private String botToken;

    public FinanTelegramBot(QueueGateway queueGateway) {
        this.queueGateway = queueGateway;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();
            Long chatId = update.getMessage().getChatId();
            String userName = update.getMessage().getFrom().getUserName();

            System.out.println("Mensagem recebida: " + text);

            IncomingChatMessage message = new IncomingChatMessage(
                    String.valueOf(chatId),
                    userName,
                    text
            );

            queueGateway.send(message);
        }
    }

    public void sendText(Long chatId, String text) {
        SendMessage sm = SendMessage.builder()
                .chatId(chatId.toString())
                .text(text)
                .build();
        try {
            execute(sm);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }
}