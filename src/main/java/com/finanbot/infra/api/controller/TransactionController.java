package com.finanbot.infra.api.controller;

import com.finanbot.core.domain.model.Transaction;
import com.finanbot.core.usecase.CreateTransactionUseCase;
import com.finanbot.core.usecase.dto.CreateTransactionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final CreateTransactionUseCase createTransactionUseCase;

    public TransactionController(CreateTransactionUseCase createTransactionUseCase) {
        this.createTransactionUseCase = createTransactionUseCase;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<Void> create(@RequestBody CreateTransactionRequest request) {
        Transaction transaction = createTransactionUseCase.execute(request);

        return ResponseEntity.created(URI.create("/api/transactions/" + transaction.getId())).build();
    }
}