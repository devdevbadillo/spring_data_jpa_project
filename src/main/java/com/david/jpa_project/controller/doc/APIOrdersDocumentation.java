package com.david.jpa_project.controller.doc;

import com.david.jpa_project.controller.advice.exceptions.ResourceNotFoundException;
import com.david.jpa_project.controller.dto.out.OrderInfoOut;
import com.david.jpa_project.controller.dto.out.PageOut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "Orders",
        description = "Orders API"
)
public interface APIOrdersDocumentation {

    @Operation(summary = "Find orders by user")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found orders by user",
                    content = @Content(schema = @Schema(implementation = PageOut.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class))
            )
    })
    ResponseEntity<PageOut<OrderInfoOut>> findOrdersByUser(
            Long userId,
            @Min(value = 0, message = "{error.input.min}") int page,
            @Min(value = 0, message = "{error.input.min}") @Max(value = 20, message = "{error.input.max}") int size
    );

    @Operation(summary = "Find order by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found order by id",
                    content = @Content(schema = @Schema(implementation = OrderInfoOut.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Order not found",
                    content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = ResourceNotFoundException.class))
            )
    })
    ResponseEntity<OrderInfoOut> findOrderById(
            Long orderId
    ) throws ResourceNotFoundException;
}
