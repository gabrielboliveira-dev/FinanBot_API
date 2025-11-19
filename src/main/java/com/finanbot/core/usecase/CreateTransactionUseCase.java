package com.finanbot.core.usecase;

import com.finanbot.core.domain.model.Account;
import com.finanbot.core.domain.model.Transaction;
import com.finanbot.core.domain.model.TransactionType;
import com.finanbot.core.usecase.dto.CreateTransactionRequest;
import com.finanbot.core.usecase.port.AccountRepository;
import com.finanbot.core.usecase.port.TransactionRepository;

public class CreateTransactionUseCase {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public CreateTransactionUseCase(TransactionRepository transactionRepository, AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    public Transaction execute(CreateTransactionRequest request) {
        Account account = accountRepository.findById(request.accountId())
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada."));

        if (!account.getUserId().equals(request.userId())) {
            throw new IllegalArgumentException("Conta não pertence ao usuário.");
        }
        if (request.type() == TransactionType.EXPENSE) {
            account.withdraw(request.amount());
        } else {
            account.deposit(request.amount());
        }

        Transaction transaction = new Transaction(
                request.userId(),
                request.accountId(),
                request.categoryId(),
                request.amount(),
                request.type(),
                request.date(),
                request.description()
        );

        Transaction savedTransaction = transactionRepository.save(transaction);
        accountRepository.save(account);
        return savedTransaction;
    }
}