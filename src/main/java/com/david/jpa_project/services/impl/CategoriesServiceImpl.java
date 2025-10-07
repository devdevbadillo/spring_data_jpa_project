package com.david.jpa_project.services.impl;

import com.david.jpa_project.model.repositories.jpql.CategoryRepository;
import com.david.jpa_project.services.interfaces.ICategoriesService;
import org.springframework.stereotype.Service;

@Service
public class CategoriesServiceImpl implements ICategoriesService {

    private final CategoryRepository categoryRepository;

    public CategoriesServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

}
