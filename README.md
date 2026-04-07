# 🤖 FinanBot: Seu Co-piloto Financeiro Pessoal no Telegram

## ✨ Visão Geral

O FinanBot é uma plataforma inovadora de gestão financeira pessoal operada de forma conversacional através do Telegram. Desenvolvido para simplificar o controle de finanças, o projeto transforma a complexa tarefa de registrar gastos e planejar orçamentos em uma experiência fluida via chat, eliminando a necessidade de planilhas e aplicativos manuais.

A arquitetura foi rigorosamente projetada com base na **Arquitetura Limpa (Clean Architecture)**, **SOLID** e **Event-Driven Architecture (EDA)**. O núcleo do sistema é uma API RESTful em Java/Spring Boot, que se comunica de forma assíncrona com microsserviços de IA (para Processamento de Linguagem Natural e Machine Learning) utilizando mensageria robusta, garantindo um sistema altamente escalável, testável e responsivo.

## 🛠️ Tecnologias e Ferramentas

A aplicação utiliza um ecossistema moderno, totalmente conteinerizado via Docker para garantir consistência entre os ambientes de desenvolvimento e produção:

-   **Linguagem de Programação:** Java 17 e Python (Serviços de IA)
-   **Framework Backend:** Spring Boot 3 (Web, Data JPA, Security, AMQP)
    -   *Justificativa:* Ecossistema robusto, maduro e de alta performance para a construção de APIs e integrações complexas.
-   **Mensageria Assíncrona:** RabbitMQ
    -   *Justificativa:* Desacopla o recebimento das mensagens do Telegram do processamento pesado de IA e banco de dados, evitando o bloqueio de threads e garantindo a resiliência do bot.
-   **Inteligência Artificial e NLU:** Rasa Open Source, Scikit-learn, Pandas (Python)
    -   *Justificativa:* Stack especializada para interpretar intenções em linguagem natural (ex: *"gastei 50 no almoço"*) e gerar projeções preditivas de fluxo de caixa.
-   **Persistência:** PostgreSQL e Spring Data JPA/Hibernate
    -   *Justificativa:* Banco de dados relacional confiável para transações financeiras (ACID), com mapeamento objeto-relacional eficiente.
-   **Migrações de Banco de Dados:** Flyway
    -   *Justificativa:* Controle de versão automatizado do esquema do banco de dados (tabelas de contas, transações, usuários).
-   **Segurança:** Spring Security, JWT (JSON Web Token) e BCrypt
    -   *Justificativa:* Proteção das rotas da API RESTful e garantia de que os dados financeiros pertencem apenas ao usuário autenticado.
-   **Integração Externa:** Telegram Bots API (Long Polling/Webhooks)
    -   *Justificativa:* Interface principal com o usuário final, escolhida pela facilidade de uso e suporte a comandos ricos.
-   **Testes:** JUnit 5, Mockito, Testcontainers, RestAssured
    -   *Justificativa:* Cobertura completa englobando testes unitários e testes de integração de ponta a ponta (E2E) com banco de dados e mensageria reais em containers isolados.
-   **CI/CD:** GitHub Actions
    -   *Justificativa:* Pipeline automatizado que valida o build, executa a suíte de testes com Testcontainers e garante a qualidade a cada novo *commit*.
-   **Observabilidade:** Prometheus, Grafana, Loki e Promtail
    -   *Justificativa:* Stack completa para monitoramento de métricas de saúde da aplicação, dashboards visuais e agregação centralizada de logs.

## 🏛️ Arquitetura

O projeto adota estritamente a **Clean Architecture**, isolando as regras de negócio das tecnologias de entrega (APIs, Banco de Dados, Mensageria):

-   **`core.domain`**: O coração do software. Contém as entidades ricas (`User`, `Account`, `Transaction`, `Category`) e Value Objects, encapsulando as regras de negócio puras sem dependência de frameworks.
-   **`core.usecase`**: Contém a lógica da aplicação (`CreateTransactionUseCase`, `ProcessChatUseCase`). Orquestra as regras do domínio utilizando interfaces (Ports) para se comunicar com o mundo externo.
-   **`infrastructure`**: A camada de adaptadores. Implementa os repositórios JPA, expõe os Controllers REST (API), gerencia a segurança (Filtros JWT), assina e publica na fila do RabbitMQ e faz a ponte com a API do Telegram.

## ✅ Funcionalidades Implementadas

### 💬 Interação e Automação via Telegram
-   **Registro Conversacional (NLU):** Adição de despesas informando apenas o texto (ex: *"Gastei R$ 120 com Uber"*).
-   **Assincronismo:** Respostas instantâneas do bot, com processamento financeiro ocorrendo em background.
-   **Vínculo de Identidade:** Associação segura do `chatId` do Telegram com a conta do usuário cadastrada na API.

### 💰 Gestão Financeira Centralizada
-   **Múltiplas Contas:** Criação e gestão de saldos em diferentes contas (ex: Corrente, Poupança, Carteira).
-   **Transações e Categorias:** Lançamento de receitas e despesas vinculadas a categorias personalizáveis para relatórios precisos.
-   **Metas Financeiras:** Estabelecimento de objetivos com valores e prazos (ex: "Comprar Carro").

### 🔐 Segurança da Informação
-   **Autenticação JWT:** API protegida para gerenciamento de dados via Dashboard Web/Mobile paralelo ao Bot.
-   **Proteção de Dados Sensíveis:** Senhas criptografadas (BCrypt) e acesso restrito por `userId`.

## ⚙️ Como Rodar o Projeto

1.  **Pré-requisitos:**
    -   Tenha o **Java JDK 17+** e o **Maven** instalados.
    -   Tenha o **Docker** e o **Docker Compose** em execução.
    -   Um token de bot do Telegram (crie um conversando com o `@BotFather` no Telegram).

2.  **Clone o Repositório:**
    ```bash
    git clone [https://github.com/gabrielboliveira-dev/FinanBot_API](https://github.com/gabrielboliveira-dev/FinanBot_API)
    cd FinanBot_API
    ```

3.  **Configurações de Ambiente (`.env`):**
    -   Crie um arquivo `.env` na raiz do projeto e preencha as variáveis abaixo:
        ```env
        # Banco de Dados
        DB_USERNAME=postgres
        DB_PASSWORD=sua_senha_segura
        DB_URL=jdbc:postgresql://db:5432/finanbot
        
        # Segurança da API
        JWT_SECRET=SUA_CHAVE_SECRETA_MUITO_LONGA_E_COMPLEXA_AQUI_256_BITS
        JWT_EXPIRATION=86400000
        
        # Telegram Bot
        TELEGRAM_BOT_USERNAME=SeuBotUsername
        TELEGRAM_BOT_TOKEN=123456789:ABCDEFGHIJKLMNOPQRSTUVWXYZ
        ```

4.  **Construir e Iniciar os Containers:**
    ```bash
    docker-compose up --build -d
    ```
    -   Este comando subirá toda a infraestrutura: A aplicação Spring Boot, o PostgreSQL, o RabbitMQ e a stack de observabilidade.

5.  **Acessar o Sistema:**
    -   **Documentação Interativa (Swagger):** Acesse `http://localhost:8080/swagger-ui.html`.
    -   **Bot do Telegram:** Procure pelo username do seu bot no Telegram e envie um `/start`!

## 🗺️ Endpoints Principais (API REST)

*A API serve como base para a criação de cadastros complexos, enquanto o Bot do Telegram atua como a interface do dia a dia.*

| Método | Rota | Descrição |
| :--- | :--- | :--- |
| `POST` | `/api/v1/auth/register` | Registra um novo usuário no sistema. |
| `POST` | `/api/v1/auth/login` | Autentica um usuário e retorna o token JWT. |
| `POST` | `/api/v1/accounts` | Cria uma nova conta bancária ou carteira. |
| `POST` | `/api/v1/categories` | Cadastra uma nova categoria de transação. |
| `POST` | `/api/v1/transactions` | Registra uma nova despesa ou receita manualmente. |
| `POST` | `/api/v1/goals` | Cria uma nova meta financeira associada ao usuário. |

## 🌟 Demonstração de Boas Práticas

Este projeto foi desenhado para ser um portfólio de engenharia de software avançada:

-   **Event-Driven Architecture:** Uso do RabbitMQ e do padrão Producer/Consumer para evitar bloqueio de concorrência nas requisições web.
-   **Testes com Testcontainers:** Testes de integração que sobem infraestrutura real no Docker, garantindo que o código funciona perfeitamente com o PostgreSQL e o RabbitMQ antes de ir para produção.
-   **Ports and Adapters:** Inversão de dependência permitindo que o NLU mude de Regex local para uma API externa do Rasa/Python sem alterar uma única linha da regra de negócio (Core).
-   **CI/CD Pipeline:** Integração contínua automatizada via GitHub Actions.
-   **Design Orientado a Domínio (DDD):** Modelagem focada nas entidades de negócio reais e em suas invariantes estruturais.