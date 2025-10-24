package com.david.jpa_project.services.impl;

import com.david.jpa_project.controller.advice.exceptions.ResourceNotFoundException;
import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.controller.dto.out.ProductOut;
import com.david.jpa_project.controller.dto.request.RequestCreateProductDTO;
import com.david.jpa_project.model.entities.entity.Category;
import com.david.jpa_project.model.entities.entity.Product;
import com.david.jpa_project.model.mappers.ProductMapper;
import com.david.jpa_project.model.repositories.jpql.ProductRepository;
import com.david.jpa_project.model.repositories.store_procedures.CategoryRepository;
import com.david.jpa_project.services.interfaces.IProductsService;
import org.springframework.transaction.annotation.Transactional;
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
    private final CategoryRepository categoryRepository;
    private final ProductMapper productMapper;

    public ProductsServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.productMapper = productMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public PageOut<ProductOut> getProductsByCategory(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAllProductsByCategoryId(categoryId, pageable);

        log.info("Products by category: {}", products);
        return productMapper.toPageOut(products);
    }

    @Override
    @Transactional(readOnly = true)
    public PageOut<ProductOut> getProductsByCategories(List<Long> categories, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAllProductsByCategories(categories, pageable);

        log.info("Products by categories: {}", products);
        return productMapper.toPageOut(products);
    }

    @Override
    @Transactional
    public void createProduct(RequestCreateProductDTO product, String userExecutor) throws ResourceNotFoundException {
        Product productEntity = productMapper.toProductEntity(product);
        productEntity.setUserAudit(userExecutor);

        if(product.categories() != null && !product.categories().isEmpty()) {
            List<Category> categories = categoryRepository.findAllById(product.categories());

            if(categories.size() != product.categories().size()) {
                throw new ResourceNotFoundException("One or more categories not found");
            }

            productEntity.setCategories(categories);
        }

        this.productRepository.save(productEntity);
    }
}
