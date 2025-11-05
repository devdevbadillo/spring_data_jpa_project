package com.david.jpa_project.model.mappers;

import com.david.jpa_project.controller.dto.out.ProductOut;
import com.david.jpa_project.controller.dto.request.RequestCreateProductDTO;
import com.david.jpa_project.model.entities.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper extends BaseMapper<ProductOut, Product> {

    @Mapping(target = "categories", ignore = true)
    Product toProductEntity(RequestCreateProductDTO product);
}
