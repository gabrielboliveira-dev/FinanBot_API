package com.finanbot.core.usecase;

import com.finanbot.core.domain.model.User;
import com.finanbot.core.usecase.dto.CreateUserRequest;
import com.finanbot.core.usecase.port.UserRepository;

public class CreateUserUseCase {

    private final UserRepository userRepository;

    public CreateUserUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        if (userRepository.existsByCpf(request.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        User newUser = new User(
                request.name(),
                request.email(),
                request.cpf(),
                request.password()
        );
        return userRepository.save(newUser);
    }
}