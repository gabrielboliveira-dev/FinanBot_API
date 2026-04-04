package com.finanbot.infra.api.controller;

import com.finanbot.core.domain.model.SharedExpense;
import com.finanbot.core.usecase.CreateSharedExpenseUseCase;
import com.finanbot.core.usecase.dto.CreateSharedExpenseRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/shared-expenses")
public class SharedExpenseController {

    private final CreateSharedExpenseUseCase createSharedExpenseUseCase;

    public SharedExpenseController(CreateSharedExpenseUseCase createSharedExpenseUseCase) {
        this.createSharedExpenseUseCase = createSharedExpenseUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateSharedExpenseRequest request) {

        SharedExpense createdExpense = createSharedExpenseUseCase.execute(
                request.groupId(),
                request.paidByUserId(),
                request.description(),
                request.amount(),
                request.date()
        );
        return ResponseEntity.created(URI.create("/api/shared-expenses/" + createdExpense.getId())).build();
    }
}