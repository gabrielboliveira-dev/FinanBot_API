package com.finanbot.infra.api.controller;

import com.finanbot.core.domain.model.RecurringExpense;
import com.finanbot.core.usecase.CreateRecurringExpenseUseCase;
import com.finanbot.core.usecase.dto.CreateRecurringExpenseRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/recurring-expenses")
public class RecurringExpenseController {

    private final CreateRecurringExpenseUseCase createRecurringExpenseUseCase;

    public RecurringExpenseController(CreateRecurringExpenseUseCase createRecurringExpenseUseCase) {
        this.createRecurringExpenseUseCase = createRecurringExpenseUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateRecurringExpenseRequest request) {

        RecurringExpense createdExpense = createRecurringExpenseUseCase.execute(
                request.userId(),
                request.categoryId(),
                request.description(),
                request.amount(),
                request.frequency(),
                request.nextDueDate()
        );
        return ResponseEntity.created(URI.create("/api/recurring-expenses/" + createdExpense.getId())).build();
    }
}