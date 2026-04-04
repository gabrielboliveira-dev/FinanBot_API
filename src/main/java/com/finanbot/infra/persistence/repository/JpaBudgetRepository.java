package com.finanbot.infra.persistence.repository;

import com.finanbot.infra.persistence.entity.BudgetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaBudgetRepository extends JpaRepository<BudgetEntity, UUID> {
    List<BudgetEntity> findByUserId(UUID userId);
}