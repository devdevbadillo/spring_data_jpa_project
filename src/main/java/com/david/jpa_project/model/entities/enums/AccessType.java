package com.david.jpa_project.model.entities.enums;

import lombok.Getter;

@Getter
public enum AccessType {
    ACCESS_TO_SHOP_API("ACCESS_SHOP_TO_API"),
    ACCESS_TO_RECOVERY_ACCOUNT("ACCESS_TO_RECOVERY_ACCOUNT"),
    ACCESS_TO_VERIFY_ACCOUNT("ACCESS_TO_VERIFY_ACCOUNT"),
    ACCESS_TO_ADMIN_API("ACCESS_TO_ADMIN_API");

    private final String value;

    AccessType(String value) {
        this.value = value;
    }
}
