package com.david.jpa_project.services.impl;

import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.controller.dto.out.ProductOut;
import com.david.jpa_project.model.entities.entity.Product;
import com.david.jpa_project.model.mappers.ProductMapper;
import com.david.jpa_project.model.repositories.jpql.ProductRepository;
import com.david.jpa_project.services.interfaces.IProductsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductsServiceImpl implements IProductsService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public ProductsServiceImpl(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    @Override
    public PageOut<ProductOut> getProductsByCategory(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAllProductsByCategoryId(categoryId, pageable);
        return productMapper.toPageOut(products);
    }

    @Override
    public PageOut<ProductOut> getProductsByCategories(List<Long> categories, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAllProductsByCategories(categories, pageable);
        return productMapper.toPageOut(products);
    }
}
