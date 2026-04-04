package com.finanbot.core.usecase.port;

import com.finanbot.core.domain.model.Investment;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface InvestmentRepository {
    Investment save(Investment investment);
    Optional<Investment> findById(UUID id);
    List<Investment> findByUserId(UUID userId);
}