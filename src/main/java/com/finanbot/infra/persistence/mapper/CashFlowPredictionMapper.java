package com.finanbot.infra.persistence.mapper;

import com.finanbot.core.domain.model.CashFlowPrediction;
import com.finanbot.infra.persistence.entity.CashFlowPredictionEntity;
import com.finanbot.infra.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CashFlowPredictionMapper {

    default CashFlowPrediction toDomain(CashFlowPredictionEntity entity) {
        if (entity == null) return null;
        return new CashFlowPrediction(
            entity.getId(),
            entity.getUser() != null ? entity.getUser().getId() : null,
            entity.getTargetDate(),
            entity.getPredictedBalance()
        );
    }

    @Mapping(source = "userId", target = "user", qualifiedByName = "idToUserEntity")
    CashFlowPredictionEntity toEntity(CashFlowPrediction prediction);
    
    @org.mapstruct.Named("idToUserEntity")
    default UserEntity idToUserEntity(java.util.UUID id) {
        if (id == null) return null;
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }
}