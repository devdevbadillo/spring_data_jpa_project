package com.david.jpa_project.repository;

import com.david.jpa_project.factory.CategoryFactory;
import com.david.jpa_project.factory.ProductFactory;
import com.david.jpa_project.model.entities.entity.Category;
import com.david.jpa_project.model.entities.entity.Product;
import com.david.jpa_project.model.repositories.jpql.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
public class ProductRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ProductRepository productRepository;

    private Product testProduct;
    private Category testCategory;

    private static final Pageable PAGEABLE = PageRequest.of(0, 10);

    @BeforeEach
    public void setUp() {
        testCategory = entityManager.persist(CategoryFactory.createCategory());

        testProduct = ProductFactory.createProduct();
        testProduct.setCategories(List.of(testCategory));
        entityManager.persist(testProduct);
    }

    @Test
    void findProductsByCategory_WhenCategoryExists_ReturnsProducts() {
        Long categoryId = testCategory.getId();

        var result = productRepository.findAllProductsByCategoryId(categoryId, PAGEABLE);

        var productInfo = result.getContent().getFirst();

       assertEquals(testProduct.getName(), productInfo.getName());
    }

    @Test
    void findProductsByCategory_WhenCategoryNotExists_ReturnsEmptyList() {
        Long categoryId = testCategory.getId() + 1;

        var result = productRepository.findAllProductsByCategoryId(categoryId, PAGEABLE);

        assertEquals(0, result.getContent().size());
    }


    @Test
    void findProductsByCategories_WhenCategoriesExists_ReturnsProducts() {
        Long categoryId = testCategory.getId();
        List<Long> categories = List.of(categoryId);

        var result = productRepository.findAllProductsByCategories(categories, PAGEABLE);

        var productInfo = result.getContent().getFirst();

        assertEquals(testProduct.getName(), productInfo.getName());
    }
}
