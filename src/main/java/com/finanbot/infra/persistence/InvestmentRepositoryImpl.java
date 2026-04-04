package com.finanbot.infra.persistence;

import com.finanbot.core.domain.model.Investment;
import com.finanbot.core.usecase.port.InvestmentRepository;
import com.finanbot.infra.persistence.entity.InvestmentEntity;
import com.finanbot.infra.persistence.mapper.InvestmentMapper;
import com.finanbot.infra.persistence.repository.JpaInvestmentRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class InvestmentRepositoryImpl implements InvestmentRepository {

    private final JpaInvestmentRepository jpaInvestmentRepository;
    private final InvestmentMapper investmentMapper;

    public InvestmentRepositoryImpl(JpaInvestmentRepository jpaInvestmentRepository, InvestmentMapper investmentMapper) {
        this.jpaInvestmentRepository = jpaInvestmentRepository;
        this.investmentMapper = investmentMapper;
    }

    @Override
    public Investment save(Investment investment) {
        InvestmentEntity entity = investmentMapper.toEntity(investment);
        InvestmentEntity savedEntity = jpaInvestmentRepository.save(entity);
        return investmentMapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Investment> findById(UUID id) {
        return jpaInvestmentRepository.findById(id).map(investmentMapper::toDomain);
    }

    @Override
    public List<Investment> findByUserId(UUID userId) {
        return jpaInvestmentRepository.findByUserId(userId).stream()
                .map(investmentMapper::toDomain)
                .collect(Collectors.toList());
    }
}