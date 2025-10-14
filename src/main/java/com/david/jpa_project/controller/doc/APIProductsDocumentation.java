package com.david.jpa_project.controller.doc;

import com.david.jpa_project.controller.dto.out.PageOut;
import com.david.jpa_project.controller.dto.out.ProductOut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Tag(name = "Products", description = "Products API")
public interface APIProductsDocumentation {

    @Operation(summary = "Get products by category")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Products found",
                    content = @Content(schema = @Schema(implementation = PageOut.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = Error.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Products not found",
                    content = @Content(schema = @Schema(implementation = Error.class))
            )
    })
    ResponseEntity<PageOut<ProductOut>> getProductsByCategory(
            Long categoryId,
            @Min(value = 0, message = "{error.input.min}") int page,
            @Min(value = 0, message = "{error.input.min}") @Max(value = 20, message = "{error.input.max}") int size
    );

    @Operation(summary = "Get products by categories")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Products found",
                    content = @Content(schema = @Schema(implementation = PageOut.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = Error.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Products not found",
                    content = @Content(schema = @Schema(implementation = Error.class))
            )
    })
    ResponseEntity<PageOut<ProductOut>> getProductsByCategories(
            List<Long> categories,
            @Min(value = 0, message = "{error.input.min}") int page,
            @Min(value = 0, message = "{error.input.min}") @Max(value = 20, message = "{error.input.max}") int size
    );
}
