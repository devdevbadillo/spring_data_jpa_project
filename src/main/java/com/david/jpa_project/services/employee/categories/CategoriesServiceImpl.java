package com.david.jpa_project.services.employee.categories;

import com.david.jpa_project.model.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoriesServiceImpl implements ICategoriesService {

    private final CategoryRepository categoryRepository;

    public CategoriesServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

}
