package com.finanbot.infra.persistence;

import com.finanbot.core.domain.model.CashFlowPrediction;
import com.finanbot.core.usecase.port.CashFlowPredictionRepository;
import com.finanbot.infra.persistence.entity.CashFlowPredictionEntity;
import com.finanbot.infra.persistence.mapper.CashFlowPredictionMapper;
import com.finanbot.infra.persistence.repository.JpaCashFlowPredictionRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CashFlowPredictionRepositoryImpl implements CashFlowPredictionRepository {

    private final JpaCashFlowPredictionRepository jpaCashFlowPredictionRepository;
    private final CashFlowPredictionMapper cashFlowPredictionMapper;

    public CashFlowPredictionRepositoryImpl(JpaCashFlowPredictionRepository jpaCashFlowPredictionRepository, CashFlowPredictionMapper cashFlowPredictionMapper) {
        this.jpaCashFlowPredictionRepository = jpaCashFlowPredictionRepository;
        this.cashFlowPredictionMapper = cashFlowPredictionMapper;
    }

    @Override
    public CashFlowPrediction save(CashFlowPrediction prediction) {
        CashFlowPredictionEntity entity = cashFlowPredictionMapper.toEntity(prediction);
        CashFlowPredictionEntity savedEntity = jpaCashFlowPredictionRepository.save(entity);
        return cashFlowPredictionMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<CashFlowPrediction> findById(UUID id) {
        return jpaCashFlowPredictionRepository.findById(id).map(cashFlowPredictionMapper::toDomain);
    }

    @Override
    public List<CashFlowPrediction> findByUserId(UUID userId) {
        return jpaCashFlowPredictionRepository.findByUserId(userId).stream()
                .map(cashFlowPredictionMapper::toDomain)
                .collect(Collectors.toList());
    }
}