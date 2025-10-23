package com.david.jpa_project.controller.dto.out;

public record CustomerStatsOut(
        Long totalCustomers,
        Long enabledCustomers,
        Long verifiedCustomers
) {
}
