package com.david.jpa_project.controller.dto.request;

public record AddressInfo(
        String country,
        String city,
        String state,
        String zipCode,
        String street
) {
}
