package com.finanbot.infra.persistence.repository;

import com.finanbot.infra.persistence.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface SpringCategoryRepository extends JpaRepository<CategoryEntity, UUID> {
    boolean existsByNameAndUserId(String name, UUID userId);
    List<CategoryEntity> findAllByUserId(UUID userId);
}