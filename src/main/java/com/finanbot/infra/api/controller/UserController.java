package com.finanbot.infra.api.controller;

import com.finanbot.core.domain.model.User;
import com.finanbot.core.usecase.CreateUserUseCase;
import com.finanbot.core.usecase.dto.CreateUserRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    public UserController(CreateUserUseCase createUserUseCase) {
        this.createUserUseCase = createUserUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateUserRequest request) {
        User createdUser = createUserUseCase.execute(request);

        return ResponseEntity.created(URI.create("/api/users/" + createdUser.getId())).build();
    }
}