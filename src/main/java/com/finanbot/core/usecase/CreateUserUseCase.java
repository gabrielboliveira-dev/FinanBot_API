package com.finanbot.core.usecase;

import com.finanbot.core.domain.model.User;
import com.finanbot.core.usecase.dto.CreateUserRequest;
import com.finanbot.core.usecase.port.PasswordEncoder;
import com.finanbot.core.usecase.port.UserRepository;

public class CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CreateUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User execute(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado.");
        }

        if (userRepository.existsByCpf(request.cpf())) {
            throw new IllegalArgumentException("CPF já cadastrado.");
        }

        String encodedPassword = passwordEncoder.encode(request.password());

        User newUser = new User(
                request.name(),
                request.email(),
                request.cpf(),
                encodedPassword
        );

        return userRepository.save(newUser);
    }
}