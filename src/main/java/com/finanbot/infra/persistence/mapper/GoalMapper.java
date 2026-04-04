package com.finanbot.infra.persistence.mapper;

import com.finanbot.core.domain.model.Goal;
import com.finanbot.infra.persistence.entity.GoalEntity;
import com.finanbot.infra.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GoalMapper {

    default Goal toDomain(GoalEntity entity) {
        if (entity == null) return null;
        return new Goal(
            entity.getId(),
            entity.getUser() != null ? entity.getUser().getId() : null,
            entity.getName(),
            entity.getTargetAmount(),
            entity.getCurrentAmount(),
            entity.getDeadline(),
            entity.getCreatedAt()
        );
    }

    @Mapping(source = "userId", target = "user", qualifiedByName = "idToUserEntity")
    GoalEntity toEntity(Goal goal);
    
    @org.mapstruct.Named("idToUserEntity")
    default UserEntity idToUserEntity(java.util.UUID id) {
        if (id == null) return null;
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }
}