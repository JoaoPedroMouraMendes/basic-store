package com.store.api.domain.product;

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
    private Department department;
}
