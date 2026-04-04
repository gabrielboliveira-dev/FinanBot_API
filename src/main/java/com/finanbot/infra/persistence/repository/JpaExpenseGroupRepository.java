package com.finanbot.infra.persistence.repository;

import com.finanbot.infra.persistence.entity.ExpenseGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaExpenseGroupRepository extends JpaRepository<ExpenseGroupEntity, UUID> {
    List<ExpenseGroupEntity> findByCreatedById(UUID userId);
}