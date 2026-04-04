package com.finanbot.infra.persistence.mapper;

import com.finanbot.core.domain.model.Budget;
import com.finanbot.infra.persistence.entity.BudgetEntity;
import com.finanbot.infra.persistence.entity.CategoryEntity;
import com.finanbot.infra.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface BudgetMapper {

    default Budget toDomain(BudgetEntity entity) {
        if (entity == null) return null;
        return new Budget(
            entity.getId(),
            entity.getUser() != null ? entity.getUser().getId() : null,
            entity.getCategory() != null ? entity.getCategory().getId() : null,
            entity.getAmount(),
            entity.getPeriod(),
            entity.getStartDate(),
            entity.getEndDate()
        );
    }

    @Mapping(source = "userId", target = "user", qualifiedByName = "idToUserEntity")
    @Mapping(source = "categoryId", target = "category", qualifiedByName = "idToCategoryEntity")
    BudgetEntity toEntity(Budget budget);
    
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