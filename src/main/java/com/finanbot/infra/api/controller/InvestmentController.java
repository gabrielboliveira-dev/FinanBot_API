package com.finanbot.infra.api.controller;

import com.finanbot.core.domain.model.Investment;
import com.finanbot.core.usecase.CreateInvestmentUseCase;
import com.finanbot.core.usecase.dto.CreateInvestmentRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {

    private final CreateInvestmentUseCase createInvestmentUseCase;

    public InvestmentController(CreateInvestmentUseCase createInvestmentUseCase) {
        this.createInvestmentUseCase = createInvestmentUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateInvestmentRequest request) {

        Investment createdInvestment = createInvestmentUseCase.execute(
                request.userId(),
                request.name(),
                request.type(),
                request.quantity(),
                request.purchasePrice(),
                request.purchaseDate()
        );
        return ResponseEntity.created(URI.create("/api/investments/" + createdInvestment.getId())).build();
    }
}