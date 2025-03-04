package com.store.api.controllers;

import com.store.api.domain.department.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity getAllDepartments() {
        var allDepartments = departmentService.getAllDepartments();
        return ResponseEntity.ok(allDepartments);
    }

    @PostMapping
    public ResponseEntity createDepartment(@RequestBody @Validated PostRequestDepartment data) {
        departmentService.createDepartment(data);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateDepartment(
            @PathVariable UUID id,
            @RequestBody @Validated PutRequestDepartment data) {
        var department = departmentService.updateDepartment(id, data);
        if (department != null)
            return ResponseEntity.ok(department);

        throw new EntityNotFoundException();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDepartment(@PathVariable UUID id) {
        if (departmentService.deleteDepartment(id)) {
            return ResponseEntity.ok().build();
        }
        throw new EntityNotFoundException();
    }
}
