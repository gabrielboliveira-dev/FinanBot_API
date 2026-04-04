package com.finanbot.core.usecase;

import com.finanbot.core.domain.model.Investment;
import com.finanbot.core.usecase.port.InvestmentRepository;
import com.finanbot.core.usecase.port.UserRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class CreateInvestmentUseCase {

    private final InvestmentRepository investmentRepository;
    private final UserRepository userRepository;

    public CreateInvestmentUseCase(InvestmentRepository investmentRepository, UserRepository userRepository) {
        this.investmentRepository = investmentRepository;
        this.userRepository = userRepository;
    }

    public Investment execute(UUID userId, String name, String type, BigDecimal quantity, BigDecimal purchasePrice, LocalDateTime purchaseDate) {
        if (userRepository.findById(userId).isEmpty()) {
            throw new IllegalArgumentException("User not found.");
        }

        Investment investment = new Investment(userId, name, type, quantity, purchasePrice, purchaseDate);
        return investmentRepository.save(investment);
    }
}