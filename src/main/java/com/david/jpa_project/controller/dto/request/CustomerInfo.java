package com.david.jpa_project.controller.dto.request;

public record CustomerInfo(
        String name,
        String lastname,
        String email,
        String password,
        String phoneNumber,
        AddressInfo address
) {
}
