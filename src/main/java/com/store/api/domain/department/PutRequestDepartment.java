package com.store.api.domain.department;

import jakarta.validation.constraints.NotBlank;

public record PutRequestDepartment(
        @NotBlank
        String name
) { }
