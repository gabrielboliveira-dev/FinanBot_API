package com.finanbot.infra.persistence.mapper;

import com.finanbot.core.domain.model.User;
import com.finanbot.infra.persistence.entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserEntity toEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCpf(),
                user.getPassword(),
                user.getCreatedAt(),
                user.isActive(),
                user.getTelegramChatId()
        );
    }

    public User toDomain(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getName(),
                entity.getEmail(),
                entity.getCpf(),
                entity.getPassword(),
                entity.getCreatedAt(),
                entity.isActive(),
                entity.getTelegramChatId()
        );
    }
}