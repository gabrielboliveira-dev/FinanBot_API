package com.finanbot.infra.persistence.mapper;

import com.finanbot.core.domain.model.Category;
import com.finanbot.core.domain.model.TransactionType;
import com.finanbot.infra.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryEntity toEntity(Category category) {
        return new CategoryEntity(
                category.getId(),
                category.getUserId(),
                category.getName(),
                category.getType() != null ? category.getType().name() : null
        );
    }

    public Category toDomain(CategoryEntity entity) {
        return new Category(
                entity.getId(),
                entity.getUserId(),
                entity.getName(),
                entity.getType() != null ? TransactionType.valueOf(entity.getType()) : null
        );
    }
}