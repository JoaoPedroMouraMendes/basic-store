package com.store.api.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record PutRequestProduct(
        String name,
        Integer price_in_cents,
        UUID department_id
) {
        public PutRequestProduct {
                if (name == null && price_in_cents == null && department_id == null)
                        throw new IllegalArgumentException("Pelo menos um campo deve ser preenchido");
        }
}
