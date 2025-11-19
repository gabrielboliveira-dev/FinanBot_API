package com.finanbot.infra.persistence.repository;

import com.finanbot.infra.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpringTransactionRepository extends JpaRepository<TransactionEntity, UUID> {
}