package com.finanbot.infra.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.finanbot.core.domain.model.AccountType;
import com.finanbot.core.domain.model.TransactionType;
import com.finanbot.core.usecase.dto.*;
import com.finanbot.infra.persistence.entity.AccountEntity;
import com.finanbot.infra.persistence.entity.CategoryEntity;
import com.finanbot.infra.persistence.entity.UserEntity;
import com.finanbot.infra.persistence.repository.SpringAccountRepository;
import com.finanbot.infra.persistence.repository.JpaGoalRepository;
import com.finanbot.infra.persistence.repository.SpringCategoryRepository;
import com.finanbot.infra.persistence.repository.SpringUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;
import com.finanbot.core.usecase.port.ChatGateway;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import com.finanbot.infra.telegram.FinanTelegramBot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
@Testcontainers
@ActiveProfiles("test")
public class IntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @Container
    static RabbitMQContainer rabbitMQ = new RabbitMQContainer("rabbitmq:3.12-management-alpine");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
        registry.add("spring.flyway.url", postgres::getJdbcUrl);
        registry.add("spring.flyway.user", postgres::getUsername);
        registry.add("spring.flyway.password", postgres::getPassword);
        registry.add("spring.rabbitmq.host", rabbitMQ::getHost);
        registry.add("spring.rabbitmq.port", rabbitMQ::getAmqpPort);
    }

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SpringUserRepository userRepository;

    @Autowired
    private JpaGoalRepository goalRepository;

    @Autowired
    private SpringAccountRepository accountRepository;

    @Autowired
    private SpringCategoryRepository categoryRepository;

    @MockitoBean
    private ChatGateway chatGateway;

    @MockitoBean
    private FinanTelegramBot finanTelegramBot;

    @MockitoBean
    private TelegramBotsApi telegramBotsApi;

    @BeforeEach
    void setUp() {
        goalRepository.deleteAll();
        accountRepository.deleteAll();
        categoryRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void shouldCreateUserAndRejectDuplicateEmail() throws Exception {
        CreateUserRequest request = new CreateUserRequest("João Silva", "joao@email.com", "12345678901", "senha123");

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        assertThat(userRepository.findByEmail("joao@email.com")).isPresent();

        CreateUserRequest duplicateRequest = new CreateUserRequest("Maria Silva", "joao@email.com", "98765432100", "senha123");

        try {
            mockMvc.perform(post("/api/users")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsString(duplicateRequest)))
                    .andExpect(status().isBadRequest());
        } catch (Exception e) {
            assertThat(e.getCause().getMessage()).isEqualTo("E-mail já cadastrado.");
        }
    }

    @Test
    void shouldCreateFinancialGoalAssociatedWithUser() throws Exception {
        UserEntity user = new UserEntity(UUID.randomUUID(), "Teste", "teste@teste.com", "11122233344", "pwd", LocalDateTime.now(), true, null);
        userRepository.save(user);

        CreateGoalRequest goalRequest = new CreateGoalRequest(
                user.getId(),
                "Comprar Carro",
                new BigDecimal("50000.00"),
                LocalDateTime.now().plusYears(1)
        );

        mockMvc.perform(post("/api/goals")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(goalRequest)))
                .andExpect(status().isCreated());

        var goals = goalRepository.findByUserId(user.getId());
        assertThat(goals).hasSize(1);
        assertThat(goals.get(0).getName()).isEqualTo("Comprar Carro");
        assertThat(goals.get(0).getCurrentAmount()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    void shouldCreateTransactionAndNotFailDueToBusinessRules() throws Exception {
        UserEntity user = new UserEntity(UUID.randomUUID(), "Teste Transacao", "transacao@teste.com", "99988877766", "pwd", LocalDateTime.now(), true, null);
        userRepository.save(user);

        AccountEntity account = new AccountEntity(UUID.randomUUID(), user.getId(), "Conta Corrente", AccountType.CHECKING_ACCOUNT.name(), new BigDecimal("1000.00"));
        accountRepository.save(account);

        CategoryEntity category = new CategoryEntity(UUID.randomUUID(), user.getId(), "Alimentação", TransactionType.EXPENSE.name());
        categoryRepository.save(category);

        CreateTransactionRequest transactionRequest = new CreateTransactionRequest(
                user.getId(),
                account.getId(),
                category.getId(),
                new BigDecimal("50.00"),
                TransactionType.EXPENSE,
                "Almoço",
                LocalDateTime.now()
        );

        mockMvc.perform(post("/api/transactions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transactionRequest)))
                .andExpect(status().isCreated());
    }
}