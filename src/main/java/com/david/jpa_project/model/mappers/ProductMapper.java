package com.david.jpa_project.model.mappers;

import com.david.jpa_project.controller.dto.out.ProductOut;
import com.david.jpa_project.model.entities.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductOut, Product> {
}
