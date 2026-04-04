package com.finanbot.core.usecase.port;

import com.finanbot.core.domain.model.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository {
    User save(User user);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);

    Optional<User> findById(UUID id);

    Optional<User> findByEmail(String email);

    Optional<User> findByTelegramChatId(String telegramChatId);
}