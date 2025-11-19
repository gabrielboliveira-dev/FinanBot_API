package com.finanbot.infra.api.controller;

import com.finanbot.core.domain.model.Account;
import com.finanbot.core.usecase.CreateAccountUseCase;
import com.finanbot.core.usecase.dto.CreateAccountRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final CreateAccountUseCase createAccountUseCase;

    public AccountController(CreateAccountUseCase createAccountUseCase) {
        this.createAccountUseCase = createAccountUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateAccountRequest request) {

        Account createdAccount = createAccountUseCase.execute(request);
        return ResponseEntity.created(URI.create("/api/accounts/" + createdAccount.getId())).build();
    }
}