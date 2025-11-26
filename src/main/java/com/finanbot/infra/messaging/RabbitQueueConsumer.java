package com.finanbot.infra.messaging;

import com.finanbot.core.usecase.ProcessChatUseCase;
import com.finanbot.core.usecase.dto.IncomingChatMessage;
import com.finanbot.infra.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitQueueConsumer {

    private final ProcessChatUseCase processChatUseCase;

    public RabbitQueueConsumer(ProcessChatUseCase processChatUseCase) {
        this.processChatUseCase = processChatUseCase;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_TELEGRAM_INCOMING)
    public void consume(IncomingChatMessage message) {
        System.out.println(" [📥] Consumindo mensagem: " + message.text());

        try {
            processChatUseCase.execute(message);
        } catch (Exception e) {
            System.err.println("Erro ao processar mensagem: " + e.getMessage());
        }
    }
}