package com.david.jpa_project.services.impl;

import com.david.jpa_project.model.repositories.pagination_and_sort.ProductRepository;
import com.david.jpa_project.services.interfaces.IProductsService;
import org.springframework.stereotype.Service;

@Service
public class ProductsServiceImpl implements IProductsService {

    private final ProductRepository productRepository;

    public ProductsServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

}
