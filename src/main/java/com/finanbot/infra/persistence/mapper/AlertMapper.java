package com.finanbot.infra.persistence.mapper;

import com.finanbot.core.domain.model.Alert;
import com.finanbot.infra.persistence.entity.AlertEntity;
import com.finanbot.infra.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AlertMapper {

    default Alert toDomain(AlertEntity entity) {
        if (entity == null) return null;
        return new Alert(
            entity.getId(),
            entity.getUser() != null ? entity.getUser().getId() : null,
            entity.getType(),
            entity.getMessage(),
            entity.getTriggerDate(),
            entity.isRead()
        );
    }

    @Mapping(source = "userId", target = "user", qualifiedByName = "idToUserEntity")
    AlertEntity toEntity(Alert alert);
    
    @org.mapstruct.Named("idToUserEntity")
    default UserEntity idToUserEntity(java.util.UUID id) {
        if (id == null) return null;
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }
}