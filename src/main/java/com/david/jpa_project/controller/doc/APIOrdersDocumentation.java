package com.david.jpa_project.controller.doc;

import com.david.jpa_project.controller.dto.out.OrderInfoOut;
import com.david.jpa_project.controller.dto.out.PageOut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
            )
    })
    ResponseEntity<PageOut<OrderInfoOut>> findOrdersByUser(
            @PathVariable Long userId,
            @RequestParam int page,
            @RequestParam int size
    );

    @Operation(summary = "Find order by id")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Found order by id",
                    content = @Content(schema = @Schema(implementation = OrderInfoOut.class))
            )
    })
    ResponseEntity<OrderInfoOut> findOrderById(@PathVariable Long orderId);
}
