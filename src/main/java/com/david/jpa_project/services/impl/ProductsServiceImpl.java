package com.david.jpa_project.services.impl;

import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.controller.dto.out.ProductOut;
import com.david.jpa_project.metrics.Monitored;
import com.david.jpa_project.model.entities.entity.Product;
import com.david.jpa_project.model.mappers.ProductMapper;
import com.david.jpa_project.model.repositories.jpql.ProductRepository;
import com.david.jpa_project.services.interfaces.IProductsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductsServiceImpl implements IProductsService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductsServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Monitored(
            value = "get.products.by.category",
            description = "Get products by category"
    )
    public PageOut<ProductOut> getProductsByCategory(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAllProductsByCategoryId(categoryId, pageable);

        log.info("Products by category: {}", products);
        return productMapper.toPageOut(products);
    }

    @Override
    @Monitored(
            value = "get.products.by.categories",
            description = "Get products by categories"
    )
    public PageOut<ProductOut> getProductsByCategories(List<Long> categories, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAllProductsByCategories(categories, pageable);

        log.info("Products by categories: {}", products);
        return productMapper.toPageOut(products);
    }
}
