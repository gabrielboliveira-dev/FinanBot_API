package com.finanbot.infra.persistence.repository;

import com.finanbot.infra.persistence.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface SpringAccountRepository extends JpaRepository<AccountEntity, UUID> {
    boolean existsByNameAndUserId(String name, UUID userId);
}