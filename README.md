# 🤖 FinanBot: Seu Co-piloto Financeiro Pessoal

*Gestão financeira inteligente e conversacional, impulsionada por uma API robusta e IA.*

---

### 🎯 Sobre o Projeto

O FinanBot é uma plataforma de gerenciamento financeiro pessoal projetada para simplificar e automatizar o controle de finanças. O projeto resolve o desafio de transformar a complexa tarefa de gestão financeira em uma experiência conversacional e proativa, utilizando uma API RESTful como núcleo e um bot no Telegram como interface principal.

A plataforma permite que os usuários registrem transações, definam metas, criem orçamentos e obtenham previsões de fluxo de caixa por meio de linguagem natural, eliminando a necessidade de planilhas e aplicativos manuais.

### 🛠️ Tecnologias e Ferramentas

A stack foi escolhida para garantir performance, escalabilidade e manutenibilidade, utilizando tecnologias modernas e consolidadas no mercado.

| Categoria              | Tecnologia                 | Justificativa                                                                                                                   |
| :--------------------- | :------------------------- | :------------------------------------------------------------------------------------------------------------------------------ |
| **Backend**            | Java 17, Spring Boot 3     | Ecossistema robusto, maduro e de alta performance para a construção de APIs RESTful seguras e escaláveis.                         |
| **Banco de Dados**     | PostgreSQL, Flyway         | Um banco de dados relacional poderoso e confiável, com migrations gerenciadas pelo Flyway para garantir consistência.             |
| **Segurança**          | Spring Security, JWT       | Implementação padrão de mercado para autenticação e autorização, garantindo a proteção dos dados do usuário.                     |
| **IA & Machine Learning** | Python, Scikit-learn       | Stack especializada para Processamento de Linguagem Natural (NLU) e para o serviço de previsão de fluxo de caixa.               |
| **Mensageria**         | RabbitMQ                   | Garante a comunicação assíncrona entre a API principal e os serviços de IA, aumentando a resiliência e a responsividade.        |
| **Testes**             | JUnit 5, Mockito, Testcontainers | Estratégia de testes completa, cobrindo desde testes de unidade até testes de integração com ambiente real (via Testcontainers). |
| **Ambiente**           | Docker, Docker Compose     | Containerização de toda a aplicação, garantindo um ambiente de desenvolvimento e produção consistente e de fácil configuração.    |
| **Observabilidade**    | Prometheus, Grafana, Loki  | Stack completa para monitoramento de métricas, visualização de dashboards e centralização de logs.                              |

### 🏗️ Arquitetura

O projeto é estruturado com base nos princípios da **Arquitetura Limpa (Clean Architecture)**, promovendo uma clara separação de responsabilidades em camadas (Domínio, Aplicação, Infraestrutura). Essa abordagem garante um baixo acoplamento entre os componentes, facilitando a testabilidade, a manutenção e a evolução do código de forma independente de frameworks e tecnologias externas.

A comunicação com os serviços de uso intensivo, como o de NLU e Machine Learning, é feita de forma assíncrona via **RabbitMQ**, o que torna a aplicação mais resiliente e ágil na resposta ao usuário.


### ✨ Funcionalidades Implementadas

*   **✍️ Registro Conversacional:** Adição de despesas e receitas via linguagem natural (`"gastei 55,90 no almoço com o cartão do Itaú"`).
*   **🤖 Comandos Intuitivos:** Interação rápida através de comandos e teclados interativos no Telegram (ex: `/resumo`, `/metas`).
*   **🔔 Alertas Proativos:** Notificações automáticas sobre fechamento de faturas, proximidade de orçamentos e contas a pagar.
*   **🔄 Detecção de Recorrência:** Identificação automática de despesas recorrentes (assinaturas, contas) para facilitar o cadastro.
*   **🎯 Módulo de Metas:** Criação e acompanhamento de objetivos financeiros com prazos e valores definidos.
*   **📈 Previsão de Fluxo de Caixa:** Projeção de saldo futuro com base no histórico de transações, utilizando um serviço de Machine Learning.
*   **💰 Orçamentos por Categoria:** Definição de limites de gastos por categoria com insights para economia.
*   **👥 Gestão de Grupos (Rachadinha):** Divisão de despesas em grupo, com cálculo automático de quem deve a quem.
*   **🏦 Múltiplas Contas e Ativos:** Gerenciamento consolidado de contas correntes, cartões e carteira de investimentos.

### 🚀 Como Rodar o Projeto

Siga os passos abaixo para executar a plataforma completa em seu ambiente local.

#### 1\. Pré-requisitos

  * Java 17 JDK
  * Maven 3.8+
  * Docker e Docker Compose

#### 2\. Configuração

1.  **Clone o repositório:**
    ```bash
    git https://github.com/gabrielboliveira-dev/FinanBot_API
    cd FinanBot_API
    ```
2.  **Configure as variáveis de ambiente:**
      * Renomeie o arquivo `.env.example` para `.env`.
      * Abra o arquivo `.env` e preencha as seguintes variáveis:
          * `DB_PASSWORD`: Senha para o banco de dados PostgreSQL.
          * `TELEGRAM_BOT_TOKEN`: Token do seu bot, obtido com o @BotFather no Telegram.
          * `TELEGRAM_BOT_USERNAME`: Username do seu bot.
          * `JWT_SECRET`: Uma chave secreta longa e segura para gerar os tokens JWT.

#### 3\. Executando com Docker Compose

O `docker-compose.yml` está configurado para orquestrar todos os contêineres necessários (Aplicação Spring Boot, Serviço ML Python, Banco de Dados, Fila, NLU, Prometheus, Grafana, Loki e Promtail).

Execute o comando na raiz do projeto:

```bash
docker-compose up --build
```

A aplicação pode levar alguns minutos para iniciar todos os serviços pela primeira vez.

### Uso da API RESTful

Após a inicialização, a API RESTful estará disponível em `http://localhost:8080`.

A documentação interativa da API (Swagger UI), gerada pelo Springdoc, pode ser acessada em:
**[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

Testes de ponta a ponta (E2E) para a API podem ser executados usando a classe `FinanBotE2ETest` (localizada em `src/test/java/com/finanbot/e2e/FinanBotE2ETest.java`), que utiliza Testcontainers para orquestrar o ambiente e RestAssured para interagir com os endpoints.

### ⭐ Melhoria Futura: Conexão com Open Finance

A integração com o Open Finance Brasil é a **visão de longo prazo** e o recurso mais poderoso do roadmap do FinanBot. Ele permitirá a importação e categorização automática de transações diretamente das suas contas bancárias.

> **Observação Importante:** Devido aos complexos requisitos regulatórios e de certificação de segurança exigidos pelo Banco Central, não é viável para uma pessoa física ou um projeto de código aberto lançar esta funcionalidade para o público geral em um ambiente de produção. A implementação atual está focada nos ambientes de **Sandbox** do Open Finance para desenvolvimento e prova de conceito.

### Como Contribuir

Contribuições são muito bem-vindas\! Se você tem ideias para novas funcionalidades, melhorias ou encontrou algum bug, sinta-se à vontade para abrir uma *Issue* ou um *Pull Request*.

1.  Faça um *Fork* do projeto.
2.  Crie uma nova *Branch* (`git checkout -b feature/sua-feature`).
3.  Faça o *Commit* das suas alterações (`git commit -m 'Adiciona sua-feature'`).
4.  Faça o *Push* para a *Branch* (`git push origin feature/sua-feature`).
5.  Abra um *Pull Request*.

### Licença

Este projeto está licenciado sob a Licença MIT. Veja o arquivo [LICENSE](https://www.google.com/search?q=LICENSE) para mais detalhes.
