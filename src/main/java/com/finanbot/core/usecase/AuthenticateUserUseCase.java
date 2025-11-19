package com.finanbot.core.usecase;

import com.finanbot.core.domain.model.User;
import com.finanbot.core.usecase.dto.LoginRequest;
import com.finanbot.core.usecase.dto.LoginResponse;
import com.finanbot.core.usecase.port.PasswordEncoder;
import com.finanbot.core.usecase.port.TokenGateway;
import com.finanbot.core.usecase.port.UserRepository;

public class AuthenticateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final TokenGateway tokenGateway;

    public AuthenticateUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder, TokenGateway tokenGateway) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenGateway = tokenGateway;
    }

    public LoginResponse execute(LoginRequest request) {
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new IllegalArgumentException("Usuário ou senha inválidos."));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new IllegalArgumentException("Usuário ou senha inválidos.");
        }

        String token = tokenGateway.generate(user);

        return new LoginResponse(token, "Bearer", "3600");
    }
}