package com.david.jpa_project.controller.dto.request;

import java.util.List;

public record RequestCreateOrderDTO(
        List<Long> products,
        Long address
) {
}
