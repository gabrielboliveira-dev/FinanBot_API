package com.finanbot.infra.persistence.repository;

import com.finanbot.infra.persistence.entity.SharedExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaSharedExpenseRepository extends JpaRepository<SharedExpenseEntity, UUID> {
    List<SharedExpenseEntity> findByGroupId(UUID groupId);
}