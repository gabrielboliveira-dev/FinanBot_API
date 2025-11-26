package com.finanbot.core.usecase.port;

import com.finanbot.core.usecase.dto.IncomingChatMessage;

public interface QueueGateway {
    void send(IncomingChatMessage message);
}