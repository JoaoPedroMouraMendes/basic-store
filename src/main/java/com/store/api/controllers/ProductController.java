package com.store.api.controllers;

import com.store.api.domain.department.Department;
import com.store.api.domain.department.DepartmentRepository;
import com.store.api.domain.product.PostRequestProduct;
import com.store.api.domain.product.Product;
import com.store.api.domain.product.ProductRepository;
import com.store.api.domain.product.PutRequestProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping
    public ResponseEntity getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        return ResponseEntity.ok(allProducts);
    }

    @PostMapping
    public ResponseEntity createProduct(@RequestBody @Validated PostRequestProduct data) {
        // Busca o objeto no banco de dados
        Optional<Department> optionalDepartment = departmentRepository.findById(data.department_id());
        // Valida se o objeto foi encontrado
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            productRepository.save(new Product(data, department));

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateProduct(
            @PathVariable UUID id,
            @RequestBody @Validated PutRequestProduct data) {
        // Busca o objeto no banco de dados
        Optional<Product> optionalProduct = productRepository.findById(id);
        Optional<Department> optionalDepartment = departmentRepository.findById(data.department_id());
        // Valida se o objeto foi encontrado
        if (optionalProduct.isPresent() && optionalDepartment.isPresent()) {
            Product product = optionalProduct.get();
            Department department = optionalDepartment.get();

            product.setName(data.name());
            product.setPrice_in_cents(data.price_in_cents());
            product.setDepartment(department);

            return ResponseEntity.ok(product);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteProduct(@PathVariable UUID id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productRepository.delete(product);

            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
