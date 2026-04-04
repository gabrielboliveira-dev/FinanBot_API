package com.finanbot.infra.api.controller;

import com.finanbot.core.domain.model.CashFlowPrediction;
import com.finanbot.core.usecase.CreateCashFlowPredictionUseCase;
import com.finanbot.core.usecase.dto.CreateCashFlowPredictionRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/predictions")
public class CashFlowPredictionController {

    private final CreateCashFlowPredictionUseCase createCashFlowPredictionUseCase;

    public CashFlowPredictionController(CreateCashFlowPredictionUseCase createCashFlowPredictionUseCase) {
        this.createCashFlowPredictionUseCase = createCashFlowPredictionUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateCashFlowPredictionRequest request) {

        CashFlowPrediction createdPrediction = createCashFlowPredictionUseCase.execute(
                request.userId(),
                request.targetDate(),
                request.predictedBalance()
        );
        return ResponseEntity.created(URI.create("/api/predictions/" + createdPrediction.getId())).build();
    }
}