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

    @Bean
    public CreateAlertUseCase createAlertUseCase(AlertRepository alertRepository, UserRepository userRepository) {
        return new CreateAlertUseCase(alertRepository, userRepository);
    }

    @Bean
    public CreateBudgetUseCase createBudgetUseCase(BudgetRepository budgetRepository, UserRepository userRepository) {
        return new CreateBudgetUseCase(budgetRepository, userRepository);
    }

    @Bean
    public CreateCashFlowPredictionUseCase createCashFlowPredictionUseCase(CashFlowPredictionRepository predictionRepository, UserRepository userRepository) {
        return new CreateCashFlowPredictionUseCase(predictionRepository, userRepository);
    }

    @Bean
    public CreateExpenseGroupUseCase createExpenseGroupUseCase(ExpenseGroupRepository expenseGroupRepository, UserRepository userRepository) {
        return new CreateExpenseGroupUseCase(expenseGroupRepository, userRepository);
    }

    @Bean
    public CreateGoalUseCase createGoalUseCase(GoalRepository goalRepository, UserRepository userRepository) {
        return new CreateGoalUseCase(goalRepository, userRepository);
    }

    @Bean
    public CreateInvestmentUseCase createInvestmentUseCase(InvestmentRepository investmentRepository, UserRepository userRepository) {
        return new CreateInvestmentUseCase(investmentRepository, userRepository);
    }

    @Bean
    public CreateRecurringExpenseUseCase createRecurringExpenseUseCase(RecurringExpenseRepository recurringExpenseRepository, UserRepository userRepository) {
        return new CreateRecurringExpenseUseCase(recurringExpenseRepository, userRepository);
    }

    @Bean
    public CreateSharedExpenseUseCase createSharedExpenseUseCase(SharedExpenseRepository sharedExpenseRepository, ExpenseGroupRepository expenseGroupRepository, UserRepository userRepository) {
        return new CreateSharedExpenseUseCase(sharedExpenseRepository, expenseGroupRepository, userRepository);
    }
}