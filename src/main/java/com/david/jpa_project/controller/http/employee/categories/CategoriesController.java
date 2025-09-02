package com.david.jpa_project.controller.http.employee.categories;

import com.david.jpa_project.services.employee.categories.ICategoriesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/categories")
public class CategoriesController {

    private final ICategoriesService categoriesService;

    public CategoriesController(ICategoriesService categoriesService) {
        this.categoriesService = categoriesService;
    }

}
