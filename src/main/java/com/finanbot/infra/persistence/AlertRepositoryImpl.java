package com.finanbot.infra.persistence;

import com.finanbot.core.domain.model.Alert;
import com.finanbot.core.usecase.port.AlertRepository;
import com.finanbot.infra.persistence.entity.AlertEntity;
import com.finanbot.infra.persistence.mapper.AlertMapper;
import com.finanbot.infra.persistence.repository.JpaAlertRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class AlertRepositoryImpl implements AlertRepository {

    private final JpaAlertRepository jpaAlertRepository;
    private final AlertMapper alertMapper;

    public AlertRepositoryImpl(JpaAlertRepository jpaAlertRepository, AlertMapper alertMapper) {
        this.jpaAlertRepository = jpaAlertRepository;
        this.alertMapper = alertMapper;
    }

    @Override
    public Alert save(Alert alert) {
        AlertEntity entity = alertMapper.toEntity(alert);
        AlertEntity savedEntity = jpaAlertRepository.save(entity);
        return alertMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Alert> findById(UUID id) {
        return jpaAlertRepository.findById(id).map(alertMapper::toDomain);
    }

    @Override
    public List<Alert> findByUserId(UUID userId) {
        return jpaAlertRepository.findByUserId(userId).stream()
                .map(alertMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Alert> findUnreadByUserId(UUID userId) {
        return jpaAlertRepository.findByUserIdAndIsReadFalse(userId).stream()
                .map(alertMapper::toDomain)
                .collect(Collectors.toList());
    }
}