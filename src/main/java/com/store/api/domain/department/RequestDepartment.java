package com.store.api.domain.department;

import jakarta.validation.constraints.NotBlank;

public record RequestDepartment(
        @NotBlank
        String name
) { }
