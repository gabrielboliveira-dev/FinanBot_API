package com.finanbot.infra.persistence.mapper;

import com.finanbot.core.domain.model.Category;
import com.finanbot.infra.persistence.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryEntity toEntity(Category category) {
        return new CategoryEntity(
                category.getId(),
                category.getUserId(),
                category.getName(),
                category.getType()
        );
    }

    public Category toDomain(CategoryEntity entity) {
        return new Category(
                entity.getId(),
                entity.getUserId(),
                entity.getName(),
                entity.getType()
        );
    }
}