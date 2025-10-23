package com.david.jpa_project.services.impl;

import com.david.jpa_project.controller.dto.request.RequestCreateCategoryDTO;
import com.david.jpa_project.model.entities.entity.Category;
import com.david.jpa_project.model.mappers.CategoryMapper;
import com.david.jpa_project.model.repositories.store_procedures.CategoryRepository;
import com.david.jpa_project.services.interfaces.ICategoryService;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;

    public CategoryServiceImpl(CategoryRepository  repository, CategoryMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public void createCategory(RequestCreateCategoryDTO request) {
        Category categoryEntity = mapper.toCategoryEntity(request);
        this.repository.save(categoryEntity);
    }
}
