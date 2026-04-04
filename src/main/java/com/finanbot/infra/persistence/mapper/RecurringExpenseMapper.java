package com.finanbot.infra.persistence.mapper;

import com.finanbot.core.domain.model.RecurringExpense;
import com.finanbot.infra.persistence.entity.RecurringExpenseEntity;
import com.finanbot.infra.persistence.entity.CategoryEntity;
import com.finanbot.infra.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RecurringExpenseMapper {

    default RecurringExpense toDomain(RecurringExpenseEntity entity) {
        if (entity == null) return null;
        return new RecurringExpense(
            entity.getId(),
            entity.getUser() != null ? entity.getUser().getId() : null,
            entity.getCategory() != null ? entity.getCategory().getId() : null,
            entity.getDescription(),
            entity.getAmount(),
            entity.getFrequency(),
            entity.getNextDueDate(),
            entity.isActive()
        );
    }

    @Mapping(source = "userId", target = "user", qualifiedByName = "idToUserEntity")
    @Mapping(source = "categoryId", target = "category", qualifiedByName = "idToCategoryEntity")
    RecurringExpenseEntity toEntity(RecurringExpense recurringExpense);
    
    @org.mapstruct.Named("idToUserEntity")
    default UserEntity idToUserEntity(java.util.UUID id) {
        if (id == null) return null;
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }

    @org.mapstruct.Named("idToCategoryEntity")
    default CategoryEntity idToCategoryEntity(java.util.UUID id) {
        if (id == null) return null;
        CategoryEntity category = new CategoryEntity();
        category.setId(id);
        return category;
    }
}