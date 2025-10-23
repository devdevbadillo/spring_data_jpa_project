package com.david.jpa_project.controller.dto.request;

public record EmployeeInfo(
        String name,
        String lastName,
        String email,
        String password,
        String jobTitle
) {
}
