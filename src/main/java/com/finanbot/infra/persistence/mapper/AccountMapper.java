package com.finanbot.infra.persistence.mapper;

import com.finanbot.core.domain.model.Account;
import com.finanbot.core.domain.model.AccountType;
import com.finanbot.infra.persistence.entity.AccountEntity;
import org.springframework.stereotype.Component;

@Component
public class AccountMapper {

    public AccountEntity toEntity(Account account) {
        return new AccountEntity(
                account.getId(),
                account.getUserId(),
                account.getName(),
                account.getType() != null ? account.getType().name() : null,
                account.getBalance()
        );
    }

    public Account toDomain(AccountEntity entity) {
        return new Account(
                entity.getId(),
                entity.getUserId(),
                entity.getName(),
                entity.getType() != null ? AccountType.valueOf(entity.getType()) : null,
                entity.getBalance()
        );
    }
}