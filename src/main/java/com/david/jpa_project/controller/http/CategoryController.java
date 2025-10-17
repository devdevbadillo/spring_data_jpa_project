package com.david.jpa_project.controller.http;

import com.david.jpa_project.controller.dto.request.RequestCreateCategoryDTO;
import com.david.jpa_project.services.interfaces.ICategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "v1/categories", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoryController {

    private final ICategoryService categoryService;

    public CategoryController(ICategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCategory(@RequestBody RequestCreateCategoryDTO request) {
        this.categoryService.createCategory(request);
    }
}
