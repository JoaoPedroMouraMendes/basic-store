package com.store.api.controllers;

import com.store.api.domain.department.Department;
import com.store.api.domain.department.DepartmentRepository;
import com.store.api.domain.department.PostRequestDepartment;
import com.store.api.domain.department.PutRequestDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository repository;

    @GetMapping
    public ResponseEntity getAllDepartments() {
        var allDepartments = repository.findAll();
        return ResponseEntity.ok(allDepartments);
    }

    @PostMapping
    public ResponseEntity createDepartment(@RequestBody @Validated PostRequestDepartment data) {
        repository.save(new Department(data));
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateDepartment(
            @PathVariable UUID id,
            @RequestBody @Validated PutRequestDepartment data) {
        // Busca o objeto no banco de dados
        Optional<Department> optionalDepartment = repository.findById(id);
        // Valida se o objeto foi encontrado
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            department.setName(data.name());
            return ResponseEntity.ok(department);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDepartment(@PathVariable UUID id) {
        // Busca o objeto no banco de dados
        Optional<Department> optionalDepartment = repository.findById(id);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            repository.delete(department);
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}
