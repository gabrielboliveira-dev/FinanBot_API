package com.finanbot.core.usecase.port;

import com.finanbot.core.domain.model.Category;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CategoryRepository {

    Category save(Category category);
    Optional<Category> findById(UUID id);
    List<Category> findAllByUserId(UUID userId);
    boolean existsByNameAndUserId(String name, UUID userId);
}