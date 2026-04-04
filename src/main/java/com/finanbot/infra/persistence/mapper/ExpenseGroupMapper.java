package com.finanbot.infra.persistence.mapper;

import com.finanbot.core.domain.model.ExpenseGroup;
import com.finanbot.infra.persistence.entity.ExpenseGroupEntity;
import com.finanbot.infra.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ExpenseGroupMapper {

    default ExpenseGroup toDomain(ExpenseGroupEntity entity) {
        if (entity == null) return null;
        return new ExpenseGroup(
            entity.getId(),
            entity.getName(),
            entity.getCreatedBy() != null ? entity.getCreatedBy().getId() : null,
            entity.getCreatedAt(),
            mapMembersToIds(entity.getMembers())
        );
    }

    @Mapping(source = "createdBy", target = "createdBy", qualifiedByName = "idToUserEntity")
    @Mapping(target = "members", expression = "java(mapIdsToMembers(group.getMembers()))")
    ExpenseGroupEntity toEntity(ExpenseGroup group);
    
    @org.mapstruct.Named("idToUserEntity")
    default UserEntity idToUserEntity(java.util.UUID id) {
        if (id == null) return null;
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }
    
    default List<UUID> mapMembersToIds(List<UserEntity> members) {
        if (members == null) return null;
        return members.stream().map(UserEntity::getId).collect(Collectors.toList());
    }
    
    default List<UserEntity> mapIdsToMembers(List<UUID> ids) {
        if (ids == null) return null;
        return ids.stream().map(this::idToUserEntity).collect(Collectors.toList());
    }
}