package com.david.jpa_project.services.interfaces;

import com.david.jpa_project.controller.dto.request.RequestCreateCategoryDTO;

public interface ICategoryService {

    void createCategory(RequestCreateCategoryDTO request);

}
