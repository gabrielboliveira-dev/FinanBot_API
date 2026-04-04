package com.finanbot.infra.persistence.mapper;

import com.finanbot.core.domain.model.SharedExpense;
import com.finanbot.infra.persistence.entity.SharedExpenseEntity;
import com.finanbot.infra.persistence.entity.ExpenseGroupEntity;
import com.finanbot.infra.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SharedExpenseMapper {

    default SharedExpense toDomain(SharedExpenseEntity entity) {
        if (entity == null) return null;
        return new SharedExpense(
            entity.getId(),
            entity.getGroup() != null ? entity.getGroup().getId() : null,
            entity.getPaidBy() != null ? entity.getPaidBy().getId() : null,
            entity.getDescription(),
            entity.getAmount(),
            entity.getDate()
        );
    }

    @Mapping(source = "groupId", target = "group", qualifiedByName = "idToExpenseGroupEntity")
    @Mapping(source = "paidByUserId", target = "paidBy", qualifiedByName = "idToUserEntity")
    SharedExpenseEntity toEntity(SharedExpense expense);
    
    @org.mapstruct.Named("idToUserEntity")
    default UserEntity idToUserEntity(java.util.UUID id) {
        if (id == null) return null;
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }

    @org.mapstruct.Named("idToExpenseGroupEntity")
    default ExpenseGroupEntity idToExpenseGroupEntity(java.util.UUID id) {
        if (id == null) return null;
        ExpenseGroupEntity group = new ExpenseGroupEntity();
        group.setId(id);
        return group;
    }
}