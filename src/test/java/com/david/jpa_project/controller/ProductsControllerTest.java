package com.david.jpa_project.controller;

import com.david.jpa_project.controller.http.ProductsController;
import com.david.jpa_project.factory.ProductFactory;
import com.david.jpa_project.services.interfaces.IProductsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProductsController.class)
public class ProductsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IProductsService productsService;

    private final static int PAGE = 0;
    private final static int SIZE = 10;

    @Test
    void findProductsByCategoryId_WhenCategoryExists_ReturnsProducts() throws Exception {
        // Arrange
        Long category = 1L;

        when(productsService.getProductsByCategory(category, PAGE, SIZE))
                .thenReturn(ProductFactory.createPageOut());

        // Act & Assert
        mockMvc.perform(get("/v1/products/category/{categoryId}", category)
                        .param("page", String.valueOf(PAGE))
                        .param("size", String.valueOf(SIZE))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void findProductsByCategories_WhenCategoriesExists_ReturnsProducts() throws Exception {
        // Arrange
        List<Long> categories = List.of(1L, 2L);

        when(productsService.getProductsByCategories(categories, PAGE, SIZE))
                .thenReturn(ProductFactory.createPageOut());

        // Act & Assert
        mockMvc.perform(get("/v1/products")
                        .param("categories", categories.stream().map(String::valueOf).collect(Collectors.joining(",")))
                        .param("page", String.valueOf(PAGE))
                        .param("size", String.valueOf(SIZE))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }


}
