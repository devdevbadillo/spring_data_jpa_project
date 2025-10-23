package com.david.jpa_project.factory;

import com.david.jpa_project.model.entities.entity.Category;

import java.time.Instant;

public class CategoryFactory {

    public static Category createCategory() {
        Category category = new Category();
        category.setName("Electronics");
        category.setUserAudit("test@test.com");
        category.setCreatedAt(Instant.now());
        category.setUpdatedAt(Instant.now());
        return category;
    }

}
