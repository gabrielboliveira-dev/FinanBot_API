package com.finanbot.infra.api.controller;

import com.finanbot.core.domain.model.Category;
import com.finanbot.core.usecase.CreateCategoryUseCase;
import com.finanbot.core.usecase.dto.CreateCategoryRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CreateCategoryUseCase createCategoryUseCase;

    public CategoryController(CreateCategoryUseCase createCategoryUseCase) {
        this.createCategoryUseCase = createCategoryUseCase;
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody CreateCategoryRequest request) {
        Category category = createCategoryUseCase.execute(request);

        return ResponseEntity.created(URI.create("/api/categories/" + category.getId())).build();
    }
}