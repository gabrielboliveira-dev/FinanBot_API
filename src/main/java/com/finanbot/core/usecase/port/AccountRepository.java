package com.finanbot.core.usecase.port;

import com.finanbot.core.domain.model.Account;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AccountRepository {
    Account save(Account account);
    Optional<Account> findById(UUID id);
    boolean existsByNameAndUserId(String name, UUID userId);

    List<Account> findAllByUserId(UUID userId);
}