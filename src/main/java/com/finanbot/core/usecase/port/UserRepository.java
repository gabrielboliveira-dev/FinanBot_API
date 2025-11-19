package com.finanbot.core.usecase.port;

import com.finanbot.core.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);

    Optional<User> findByEmail(String email);
}