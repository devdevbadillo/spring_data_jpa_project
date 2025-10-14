package com.david.jpa_project.controller.doc;

import com.david.jpa_project.controller.advice.exceptions.ResourceNotFoundException;
import com.david.jpa_project.controller.dto.out.AddressOut;
import com.david.jpa_project.controller.dto.out.PageOut;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.ResponseEntity;

@Tag(name = "Addresses", description = "Addresses API")
public interface APIAddressesDocumentation {

    @Operation(summary = "Get addresses by country (like)")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Addresses found",
                    content = @Content(schema = @Schema(implementation = PageOut.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = Error.class))
            )
    })
    ResponseEntity<PageOut<AddressOut>> getAddressesByCountry(
            String pattern,
            @Min(value = 0, message = "{error.input.min}") int page,
            @Min(value = 0, message = "{error.input.min}") @Max(value = 20, message = "{error.input.max}") int size,
            @Pattern(regexp = "asc|desc", message = "{error.input.orderBy}") String orderByCity
    );

    @Operation(summary = "Get addresses by city")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Addresses found",
                    content = @Content(schema = @Schema(implementation = PageOut.class))
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid input",
                    content = @Content(schema = @Schema(implementation = Error.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Addresses not found",
                    content = @Content(schema = @Schema(implementation = Error.class))
            )
    })
    ResponseEntity<PageOut<AddressOut>> getAddressesByCity(
            String city,
            @Min(value = 0, message = "{error.input.min}") int page,
            @Min(value = 0, message = "{error.input.min}") @Max(value = 20, message = "{error.input.max}") int size
    ) throws ResourceNotFoundException;
}
