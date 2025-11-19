package com.finanbot.core.usecase;

import com.finanbot.core.domain.model.Category;
import com.finanbot.core.usecase.dto.CreateCategoryRequest;
import com.finanbot.core.usecase.port.CategoryRepository;

public class CreateCategoryUseCase {

    private final CategoryRepository categoryRepository;

    public CreateCategoryUseCase(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category execute(CreateCategoryRequest request) {
        if (categoryRepository.existsByNameAndUserId(request.name(), request.userId())) {
            throw new IllegalArgumentException("Você já possui uma categoria com este nome.");
        }

        Category newCategory = new Category(
                request.userId(),
                request.name(),
                request.type()
        );

        return categoryRepository.save(newCategory);
    }
}