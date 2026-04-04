package com.finanbot.infra.persistence.repository;

import com.finanbot.infra.persistence.entity.RecurringExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaRecurringExpenseRepository extends JpaRepository<RecurringExpenseEntity, UUID> {
    List<RecurringExpenseEntity> findByUserId(UUID userId);
}