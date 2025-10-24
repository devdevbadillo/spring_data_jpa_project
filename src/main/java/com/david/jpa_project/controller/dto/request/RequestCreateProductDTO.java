package com.david.jpa_project.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record RequestCreateProductDTO(
        @NotBlank
        String name,

        String description,

        @Positive
        Double price,

        @Positive
        Integer stockQuantity,

        List<Long> categories
) {

}
