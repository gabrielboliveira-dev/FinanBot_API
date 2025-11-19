package com.finanbot.core.usecase;

import com.finanbot.core.domain.model.Account;
import com.finanbot.core.usecase.dto.CreateAccountRequest;
import com.finanbot.core.usecase.port.AccountRepository;

public class CreateAccountUseCase {
    private final AccountRepository accountRepository;

    public CreateAccountUseCase(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account execute(CreateAccountRequest request) {
        if (accountRepository.existsByNameAndUserId(request.name(), request.userId())) {
            throw new IllegalArgumentException("Já existe uma conta com este nome.");
        }

        Account newAccount = new Account(
                request.userId(),
                request.name(),
                request.type(),
                request.initialBalance()
        );

        return accountRepository.save(newAccount);
    }
}