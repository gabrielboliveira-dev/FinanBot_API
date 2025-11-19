package com.finanbot.core.usecase.port;

import com.finanbot.core.domain.model.Transaction;

public interface TransactionRepository {
    Transaction save(Transaction transaction);
}