package com.david.jpa_project.controller.http;

import com.david.jpa_project.controller.doc.APIProductsDocumentation;
import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.controller.dto.out.ProductOut;
import com.david.jpa_project.services.interfaces.IProductsService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping(value = "/v1/products", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class ProductsController implements APIProductsDocumentation {

    private final IProductsService productsService;

    public ProductsController(IProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<PageOut<ProductOut>> getProductsByCategory(
            @PathVariable Long categoryId,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ResponseEntity.ok(productsService.getProductsByCategory(categoryId, page, size));
    }

    @GetMapping
    public ResponseEntity<PageOut<ProductOut>> getProductsByCategories(
            @RequestParam List<Long> categories,
            @RequestParam int page,
            @RequestParam int size
    ) {
        return ResponseEntity.ok(productsService.getProductsByCategories(categories, page, size));
    }

}
