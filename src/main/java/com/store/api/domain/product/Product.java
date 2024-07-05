package com.store.api.domain.product;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.store.api.domain.department.Department;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "product")
@Entity(name = "product")
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
    @JoinColumn(name = "department_id")
    @JsonBackReference
    private Department department;

    public Product(PostRequestProduct requestProduct, Department department) {
        this.name = requestProduct.name();
        this.price_in_cents = requestProduct.price_in_cents();
        this.department = department;
    }
}
