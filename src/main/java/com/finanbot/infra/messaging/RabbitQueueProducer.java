package com.finanbot.infra.messaging;

import com.finanbot.core.usecase.dto.IncomingChatMessage;
import com.finanbot.core.usecase.port.QueueGateway;
import com.finanbot.infra.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class RabbitQueueProducer implements QueueGateway {

    private final RabbitTemplate rabbitTemplate;

    public RabbitQueueProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void send(IncomingChatMessage message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.QUEUE_TELEGRAM_INCOMING, message);
        System.out.println(" [x] Enviado para fila: " + message.text());
    }
}