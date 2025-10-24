package com.david.jpa_project.services.interfaces;

import com.david.jpa_project.controller.advice.exceptions.ResourceNotFoundException;
import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.controller.dto.out.ProductOut;
import com.david.jpa_project.controller.dto.request.RequestCreateProductDTO;

import java.util.List;

public interface IProductsService {
    PageOut<ProductOut> getProductsByCategory(Long categoryId, int page, int size);

    PageOut<ProductOut> getProductsByCategories(List<Long> categories, int page, int size);

    void createProduct(RequestCreateProductDTO product, String userExecutor) throws ResourceNotFoundException;
}
