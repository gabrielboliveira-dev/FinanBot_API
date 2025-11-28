package com.finanbot.infra.persistence.repository;

import com.finanbot.infra.persistence.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface SpringUserRepository extends JpaRepository<UserEntity, UUID> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);

    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByTelegramChatId(String telegramChatId);
}