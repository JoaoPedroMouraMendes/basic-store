package com.store.api.domain.product;

import com.store.api.domain.department.Department;
import com.store.api.domain.department.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;
    @Autowired
    DepartmentRepository departmentRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product createProduct(PostRequestProduct data) {
        // Busca o objeto no banco de dados
        Optional<Department> optionalDepartment = departmentRepository.findById(data.department_id());
        // Valida se o objeto foi encontrado
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            Product newProduct = new Product(data, department);
            productRepository.save(newProduct);

            return newProduct;
        }

        return null;
    }

    public Product updateProduct(UUID id, PutRequestProduct data) {
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

            return product;
        }

        return null;
    }

    public boolean deleteProduct(UUID id) {
        // Busca o objeto no banco de dados
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            productRepository.delete(product);

            return true;
        }

        return false;
    }

}
