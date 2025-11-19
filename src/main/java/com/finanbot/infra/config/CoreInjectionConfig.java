package com.finanbot.infra.config;

import com.finanbot.core.usecase.AuthenticateUserUseCase;
import com.finanbot.core.usecase.CreateUserUseCase;
import com.finanbot.core.usecase.port.PasswordEncoder;
import com.finanbot.core.usecase.port.TokenGateway;
import com.finanbot.core.usecase.port.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CoreInjectionConfig {

    @Bean
    public CreateUserUseCase createUserUseCase(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return new CreateUserUseCase(userRepository, passwordEncoder);
    }

    @Bean
    public AuthenticateUserUseCase authenticateUserUseCase(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            TokenGateway tokenGateway) {
        return new AuthenticateUserUseCase(userRepository, passwordEncoder, tokenGateway);
    }
}