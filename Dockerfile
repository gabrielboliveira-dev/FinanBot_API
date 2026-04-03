# Use uma imagem base do OpenJDK para Java 17
FROM eclipse-temurin:17-jdk-jammy

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Instala o Maven
# Baixa o Maven
RUN apt-get update && \
    apt-get install -y --no-install-recommends curl && \
    curl -fsSL https://archive.apache.org/dist/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz -o /tmp/apache-maven.tar.gz && \
    tar -xzf /tmp/apache-maven.tar.gz -C /opt && \
    rm /tmp/apache-maven.tar.gz

# Configura as variáveis de ambiente para o Maven
ENV MAVEN_HOME /opt/apache-maven-3.9.6
ENV PATH $MAVEN_HOME/bin:$PATH

# Copia o arquivo pom.xml e baixa as dependências do Maven
# Isso aproveita o cache do Docker, pois as dependências raramente mudam
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia o restante do código da aplicação
COPY src ./src

# Compila e empacota a aplicação Spring Boot
RUN mvn package -DskipTests

# Expõe a porta que a aplicação Spring Boot usa
EXPOSE 8080

# Comando para rodar a aplicação
ENTRYPOINT ["java", "-jar", "target/finanbot-0.0.1-SNAPSHOT.jar"]
