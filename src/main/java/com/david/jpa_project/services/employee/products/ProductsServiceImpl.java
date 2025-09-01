package com.david.jpa_project.services.employee.products;

import com.david.jpa_project.model.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductsServiceImpl implements IProductsService {

    private final ProductRepository productRepository;

    public ProductsServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

}
