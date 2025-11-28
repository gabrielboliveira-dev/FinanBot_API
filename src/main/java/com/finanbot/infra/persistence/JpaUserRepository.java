package com.finanbot.infra.persistence;

import com.finanbot.core.domain.model.User;
import com.finanbot.core.usecase.port.UserRepository;
import com.finanbot.infra.persistence.entity.UserEntity;
import com.finanbot.infra.persistence.mapper.UserMapper;
import com.finanbot.infra.persistence.repository.SpringUserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaUserRepository implements UserRepository {

    private final SpringUserRepository springUserRepository;
    private final UserMapper userMapper;

    public JpaUserRepository(SpringUserRepository springUserRepository, UserMapper userMapper) {
        this.springUserRepository = springUserRepository;
        this.userMapper = userMapper;
    }

    @Override
    public User save(User user) {
        UserEntity entity = userMapper.toEntity(user);
        UserEntity savedEntity = springUserRepository.save(entity);
        return userMapper.toDomain(savedEntity);
    }

    @Override
    public boolean existsByEmail(String email) {
        return springUserRepository.existsByEmail(email);
    }

    @Override
    public boolean existsByCpf(String cpf) {
        return springUserRepository.existsByCpf(cpf);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Optional<UserEntity> entityOptional = springUserRepository.findByEmail(email);
        return entityOptional.map(userMapper::toDomain);
    }

    @Override
    public Optional<User> findByTelegramChatId(String telegramChatId) {
        return springUserRepository.findByTelegramChatId(telegramChatId)
                .map(userMapper::toDomain);
    }
}