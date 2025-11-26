package com.finanbot.core.usecase.dto;

import java.io.Serializable;

public record IncomingChatMessage(
        String chatId,
        String userName,
        String text
) implements Serializable {}