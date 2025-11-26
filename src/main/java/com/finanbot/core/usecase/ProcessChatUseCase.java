package com.finanbot.core.usecase;

import com.finanbot.core.usecase.dto.IncomingChatMessage;
import com.finanbot.core.usecase.dto.NluResult;
import com.finanbot.core.usecase.port.ChatGateway;
import com.finanbot.core.usecase.port.NluGateway;

public class ProcessChatUseCase {

    private final ChatGateway chatGateway;
    private final NluGateway nluGateway;

    public ProcessChatUseCase(ChatGateway chatGateway, NluGateway nluGateway) {
        this.chatGateway = chatGateway;
        this.nluGateway = nluGateway;
    }

    public void execute(IncomingChatMessage message) {
        String chatId = message.chatId();

        NluResult result = nluGateway.predict(message.text());

        if ("create_transaction".equals(result.intent())) {
            chatGateway.sendMessage(chatId,
                    "Entendi! Você gastou R$ " + result.amount() +
                            " com " + result.description() +
                            " (Categoria provável: " + result.category() + ")");
        } else {
            chatGateway.sendMessage(chatId, "Desculpe, não entendi. Tente: 'Gastei 50 com almoço'");
        }
    }
}