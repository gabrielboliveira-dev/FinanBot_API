package com.finanbot.infra.persistence.mapper;

import com.finanbot.core.domain.model.Investment;
import com.finanbot.infra.persistence.entity.InvestmentEntity;
import com.finanbot.infra.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InvestmentMapper {

    default Investment toDomain(InvestmentEntity entity) {
        if (entity == null) return null;
        return new Investment(
            entity.getId(),
            entity.getUser() != null ? entity.getUser().getId() : null,
            entity.getName(),
            entity.getType(),
            entity.getQuantity(),
            entity.getPurchasePrice(),
            entity.getCurrentPrice(),
            entity.getPurchaseDate()
        );
    }

    @Mapping(source = "userId", target = "user", qualifiedByName = "idToUserEntity")
    InvestmentEntity toEntity(Investment investment);
    
    @org.mapstruct.Named("idToUserEntity")
    default UserEntity idToUserEntity(java.util.UUID id) {
        if (id == null) return null;
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }
}