package com.finanbot.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.finanbot.core.domain.model.User;
import com.finanbot.core.usecase.port.TokenGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Component
public class JwtTokenGateway implements TokenGateway {

    @Value("${jwt.secret}")
    private String secret;

    @Override
    public String generate(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("FinanBot API")
                    .withSubject(user.getEmail())
                    .withClaim("id", user.getId().toString())
                    .withExpiresAt(genExpirationDate())
                    .sign(algorithm);
        } catch (Exception exception) {
            throw new RuntimeException("Erro ao gerar token", exception);
        }
    }

    private Instant genExpirationDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}