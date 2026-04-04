package com.finanbot.core.usecase.port;

import com.finanbot.core.domain.model.Alert;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AlertRepository {
    Alert save(Alert alert);
    Optional<Alert> findById(UUID id);
    List<Alert> findByUserId(UUID userId);
    List<Alert> findUnreadByUserId(UUID userId);
}