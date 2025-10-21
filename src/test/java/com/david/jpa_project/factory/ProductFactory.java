package com.david.jpa_project.factory;

import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.controller.dto.out.ProductOut;
import com.david.jpa_project.model.entities.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.util.List;

public class ProductFactory {

    private final static int PAGE = 0;
    private final static int SIZE = 10;

    public static Page<Product> createPage(){
        Pageable pageable = PageRequest.of(PAGE, SIZE);
        List<Product> products = List.of(createProduct());
        return new PageImpl<>(products, pageable, products.size());
    }

    public static PageOut<ProductOut> createPageOut(){
        return PageOut.<ProductOut>builder()
                .content(List.of(createProductOut("Test")))
                .totalPages(1)
                .totalElements(1)
                .build();
    }

    public static ProductOut createProductOut(String name) {
        ProductOut product = new ProductOut();
        product.setName(name);
        product.setDescription("Product test");
        product.setPrice(100.00);
        product.setStockQuantity(2);

        return product;
    }


    public static Product createProduct() {
        Product product = new Product();
        product.setName("Laptop Dell");
        product.setDescription("Laptop Dell i7 16GB 512GB");
        product.setPrice(15000.00);
        product.setStockQuantity(30);
        product.setCreatedAt(Instant.now());
        product.setUpdatedAt(Instant.now());
        product.setUserAudit("test@test.com");
        return product;
    }
}
