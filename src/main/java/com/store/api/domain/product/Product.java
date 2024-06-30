package com.store.api.domain.product;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "product")
@Entity
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;
    private Integer price_in_cents;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Product product;
}
