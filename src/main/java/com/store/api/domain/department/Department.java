package com.store.api.domain.department;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.store.api.domain.product.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Table(name = "department")
@Entity(name = "department")
@EqualsAndHashCode(of = "id")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Department {
    @Id
    @GeneratedValue
    private UUID id;

    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Product> products;

    public Department(PostRequestDepartment requestDepartment) {
        this.name = requestDepartment.name();
    }
}
