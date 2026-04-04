package com.finanbot.infra.api.controller;

import com.finanbot.core.domain.model.Budget;
import com.finanbot.core.usecase.CreateBudgetUseCase;
import com.finanbot.core.usecase.dto.CreateBudgetRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    private final CreateBudgetUseCase createBudgetUseCase;

    public BudgetController(CreateBudgetUseCase createBudgetUseCase) {
        this.createBudgetUseCase = createBudgetUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateBudgetRequest request) {

        Budget createdBudget = createBudgetUseCase.execute(
                request.userId(),
                request.categoryId(),
                request.amount(),
                request.period(),
                request.startDate(),
                request.endDate()
        );
        return ResponseEntity.created(URI.create("/api/budgets/" + createdBudget.getId())).build();
    }
}