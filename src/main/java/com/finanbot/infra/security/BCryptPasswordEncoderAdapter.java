package com.finanbot.infra.security;

import com.finanbot.core.usecase.port.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class BCryptPasswordEncoderAdapter implements PasswordEncoder {

    private final org.springframework.security.crypto.password.PasswordEncoder springEncoder;

    public BCryptPasswordEncoderAdapter(org.springframework.security.crypto.password.PasswordEncoder springEncoder) {
        this.springEncoder = springEncoder;
    }

    @Override
    public String encode(String rawPassword) {
        return springEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(String rawPassword, String encodedPassword) {
        return springEncoder.matches(rawPassword, encodedPassword);
    }
}