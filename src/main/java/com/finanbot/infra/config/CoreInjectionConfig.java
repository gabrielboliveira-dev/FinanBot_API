package com.finanbot.infra.config;

import com.finanbot.core.usecase.*;
import com.finanbot.core.usecase.port.*;
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

    @Bean
    public CreateAccountUseCase createAccountUseCase(AccountRepository accountRepository) {
        return new CreateAccountUseCase(accountRepository);
    }

    @Bean
    public CreateTransactionUseCase createTransactionUseCase(
            TransactionRepository transactionRepository,
            AccountRepository accountRepository) {
        return new CreateTransactionUseCase(transactionRepository, accountRepository);
    }

    @Bean
    public CreateCategoryUseCase createCategoryUseCase(CategoryRepository categoryRepository) {
        return new CreateCategoryUseCase(categoryRepository);
    }

    @Bean
    public ProcessChatUseCase processChatUseCase(
            ChatGateway chatGateway,
            NluGateway nluGateway,
            UserRepository userRepository,
            CreateTransactionUseCase createTransactionUseCase,
            AccountRepository accountRepository,
            CategoryRepository categoryRepository) {

        return new ProcessChatUseCase(
                chatGateway,
                nluGateway,
                userRepository,
                createTransactionUseCase,
                accountRepository,
                categoryRepository
        );
    }
}