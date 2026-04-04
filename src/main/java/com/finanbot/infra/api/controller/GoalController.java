package com.finanbot.infra.api.controller;

import com.finanbot.core.domain.model.Goal;
import com.finanbot.core.usecase.CreateGoalUseCase;
import com.finanbot.core.usecase.dto.CreateGoalRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/goals")
public class GoalController {

    private final CreateGoalUseCase createGoalUseCase;

    public GoalController(CreateGoalUseCase createGoalUseCase) {
        this.createGoalUseCase = createGoalUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateGoalRequest request) {

        Goal createdGoal = createGoalUseCase.execute(
                request.userId(),
                request.name(),
                request.targetAmount(),
                request.deadline()
        );
        return ResponseEntity.created(URI.create("/api/goals/" + createdGoal.getId())).build();
    }
}