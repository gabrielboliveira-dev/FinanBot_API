package com.finanbot.infra.persistence.repository;

import com.finanbot.infra.persistence.entity.AlertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface JpaAlertRepository extends JpaRepository<AlertEntity, UUID> {
    List<AlertEntity> findByUserId(UUID userId);
    List<AlertEntity> findByUserIdAndIsReadFalse(UUID userId);
}