package com.finanbot.core.usecase.port;

import com.finanbot.core.domain.model.User;

public interface UserRepository {
    User save(User user);
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
}