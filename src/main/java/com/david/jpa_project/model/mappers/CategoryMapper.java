package com.david.jpa_project.model.mappers;

import com.david.jpa_project.controller.dto.request.RequestCreateCategoryDTO;
import com.david.jpa_project.model.entities.entity.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    
    Category toCategoryEntity(RequestCreateCategoryDTO category);

}
