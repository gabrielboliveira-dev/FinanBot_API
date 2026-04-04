package com.finanbot.infra.api.controller;

import com.finanbot.core.domain.model.ExpenseGroup;
import com.finanbot.core.usecase.CreateExpenseGroupUseCase;
import com.finanbot.core.usecase.dto.CreateExpenseGroupRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/expense-groups")
public class ExpenseGroupController {

    private final CreateExpenseGroupUseCase createExpenseGroupUseCase;

    public ExpenseGroupController(CreateExpenseGroupUseCase createExpenseGroupUseCase) {
        this.createExpenseGroupUseCase = createExpenseGroupUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateExpenseGroupRequest request) {

        ExpenseGroup createdGroup = createExpenseGroupUseCase.execute(
                request.name(),
                request.createdBy()
        );
        return ResponseEntity.created(URI.create("/api/expense-groups/" + createdGroup.getId())).build();
    }
}