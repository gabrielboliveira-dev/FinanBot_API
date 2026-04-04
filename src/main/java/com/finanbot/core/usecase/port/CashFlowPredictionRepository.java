package com.finanbot.core.usecase.port;

import com.finanbot.core.domain.model.CashFlowPrediction;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CashFlowPredictionRepository {
    CashFlowPrediction save(CashFlowPrediction prediction);
    Optional<CashFlowPrediction> findById(UUID id);
    List<CashFlowPrediction> findByUserId(UUID userId);
}