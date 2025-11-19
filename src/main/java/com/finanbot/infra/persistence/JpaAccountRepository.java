package com.finanbot.infra.persistence;

import com.finanbot.core.domain.model.Account;
import com.finanbot.core.usecase.port.AccountRepository;
import com.finanbot.infra.persistence.entity.AccountEntity;
import com.finanbot.infra.persistence.mapper.AccountMapper;
import com.finanbot.infra.persistence.repository.SpringAccountRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JpaAccountRepository implements AccountRepository {

    private final SpringAccountRepository springAccountRepository;
    private final AccountMapper accountMapper;

    public JpaAccountRepository(SpringAccountRepository springAccountRepository, AccountMapper accountMapper) {
        this.springAccountRepository = springAccountRepository;
        this.accountMapper = accountMapper;
    }

    @Override
    public Account save(Account account) {
        AccountEntity entity = accountMapper.toEntity(account);
        return accountMapper.toDomain(springAccountRepository.save(entity));
    }

    @Override
    public Optional<Account> findById(UUID id) {
        return springAccountRepository.findById(id).map(accountMapper::toDomain);
    }

    @Override
    public boolean existsByNameAndUserId(String name, UUID userId) {
        return springAccountRepository.existsByNameAndUserId(name, userId);
    }
}