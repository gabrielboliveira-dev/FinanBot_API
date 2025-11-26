package com.finanbot.core.usecase.port;

import com.finanbot.core.usecase.dto.NluResult;

public interface NluGateway {
    NluResult predict(String text);
}