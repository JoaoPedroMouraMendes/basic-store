package com.store.api.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PutRequestProduct(
        @NotBlank
        String name,
        @NotNull
        Integer price_in_cents,
        @NotNull
        UUID department_id
) { }
