package com.finanbot.infra.api.controller;

import com.finanbot.core.domain.model.Alert;
import com.finanbot.core.usecase.CreateAlertUseCase;
import com.finanbot.core.usecase.dto.CreateAlertRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    private final CreateAlertUseCase createAlertUseCase;

    public AlertController(CreateAlertUseCase createAlertUseCase) {
        this.createAlertUseCase = createAlertUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateAlertRequest request) {

        Alert createdAlert = createAlertUseCase.execute(
                request.userId(),
                request.type(),
                request.message(),
                request.triggerDate()
        );
        return ResponseEntity.created(URI.create("/api/alerts/" + createdAlert.getId())).build();
    }
}