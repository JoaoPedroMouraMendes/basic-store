package com.store.api.domain.department;

import jakarta.validation.constraints.NotBlank;

public record PostRequestDepartment(
        @NotBlank
        String name
) { }
