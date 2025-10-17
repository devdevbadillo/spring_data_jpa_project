package com.david.jpa_project.controller.dto.out;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductOut {
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String description;

    private Double price;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer stockQuantity;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer quantity;

    public boolean equals(Object obj){
        if(obj instanceof ProductOut other){
            return this.name.equals(other.name);
        }
        return false;
    }
}
