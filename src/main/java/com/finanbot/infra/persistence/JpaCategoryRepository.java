package com.finanbot.infra.persistence;

import com.finanbot.core.domain.model.Category;
import com.finanbot.core.usecase.port.CategoryRepository;
import com.finanbot.infra.persistence.entity.CategoryEntity;
import com.finanbot.infra.persistence.mapper.CategoryMapper;
import com.finanbot.infra.persistence.repository.SpringCategoryRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class JpaCategoryRepository implements CategoryRepository {

    private final SpringCategoryRepository springCategoryRepository;
    private final CategoryMapper categoryMapper;

    public JpaCategoryRepository(SpringCategoryRepository springCategoryRepository, CategoryMapper categoryMapper) {
        this.springCategoryRepository = springCategoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category save(Category category) {
        CategoryEntity entity = categoryMapper.toEntity(category);
        CategoryEntity saved = springCategoryRepository.save(entity);
        return categoryMapper.toDomain(saved);
    }

    @Override
    public Optional<Category> findById(UUID id) {
        return springCategoryRepository.findById(id).map(categoryMapper::toDomain);
    }

    @Override
    public List<Category> findAllByUserId(UUID userId) {
        return springCategoryRepository.findAllByUserId(userId)
                .stream()
                .map(categoryMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existsByNameAndUserId(String name, UUID userId) {
        return springCategoryRepository.existsByNameAndUserId(name, userId);
    }
}