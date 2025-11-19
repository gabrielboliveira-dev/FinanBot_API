package com.finanbot.core.usecase.port;

import com.finanbot.core.domain.model.User;

public interface TokenGateway {
    String generate(User user);
}