package com.david.jpa_project.model.entities.enums;

import lombok.Getter;

@Getter
public enum OrderStatus {

    PENDING("PENDING"),
    CANCELLED("CANCELLED"),
    COMPLETED("COMPLETED"),
    DELIVERED("DELIVERED");

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }
}
