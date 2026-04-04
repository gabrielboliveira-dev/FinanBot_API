package com.finanbot.infra.persistence.repository;

import com.finanbot.infra.persistence.entity.CashFlowPredictionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaCashFlowPredictionRepository extends JpaRepository<CashFlowPredictionEntity, UUID> {
    List<CashFlowPredictionEntity> findByUserId(UUID userId);
}