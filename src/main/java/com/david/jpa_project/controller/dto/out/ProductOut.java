package com.david.jpa_project.controller.dto.out;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class ProductOut {
    private String name;
    private String description;
    private Double price;
    private Integer stockQuantity;
}
