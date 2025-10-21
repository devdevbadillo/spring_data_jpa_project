package com.david.jpa_project.services;

import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.controller.dto.out.ProductOut;
import com.david.jpa_project.factory.ProductFactory;
import com.david.jpa_project.model.entities.entity.Product;
import com.david.jpa_project.model.mappers.ProductMapper;
import com.david.jpa_project.model.repositories.jpql.ProductRepository;
import com.david.jpa_project.services.impl.ProductsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductsServiceTest {

    @Mock
    private ProductRepository repository;

    @Mock
    private ProductMapper mapper;

    @InjectMocks
    private ProductsServiceImpl productsService;

    private final static int PAGE = 0;
    private final static int SIZE = 10;
    private static PageOut<ProductOut> mockPageOut;
    private static Page<Product> mockPage;

    @BeforeEach
    void setUp() {
        mockPageOut = ProductFactory.createPageOut();
        mockPage    = ProductFactory.createPage();
    }

    @Test
    void getProductsByCategory_WhenCategoryExists_ShouldReturnPageOut() {
        // Given
        Long categoryId = 1L;
        Pageable pageable = PageRequest.of(PAGE, SIZE);
        when(repository.findAllProductsByCategoryId(categoryId, pageable)).thenReturn(mockPage);
        when(mapper.toPageOut(any())).thenReturn(mockPageOut);

        // When
        PageOut<ProductOut> result = productsService.getProductsByCategory(categoryId, PAGE, SIZE);

        // Then
        assertThat(result).isNotNull();
    }

    @Test
    void getProductsByCategories_WhenCategoriesExists_ShouldReturnPageOut() {
        // Given
        List<Long> categories = List.of(1L, 2L);
        Pageable pageable = PageRequest.of(PAGE, SIZE);
        when(repository.findAllProductsByCategories(categories, pageable)).thenReturn(mockPage);
        when(mapper.toPageOut(any())).thenReturn(mockPageOut);

        // When
        PageOut<ProductOut> result = productsService.getProductsByCategories(categories, PAGE, SIZE);

        // Then
        assertThat(result).isNotNull();
    }


}
