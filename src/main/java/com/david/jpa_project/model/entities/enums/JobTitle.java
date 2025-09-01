package com.david.jpa_project.model.entities.enums;

import lombok.Getter;

@Getter
public enum JobTitle {
    MANAGER("MANAGER"),
    SALESMAN("SALESMAN");

    private final String value;

    JobTitle(String value) {
        this.value = value;
    }
}
