package com.finanbot.infra.persistence.repository;

import com.finanbot.infra.persistence.entity.InvestmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaInvestmentRepository extends JpaRepository<InvestmentEntity, UUID> {
    List<InvestmentEntity> findByUserId(UUID userId);
}