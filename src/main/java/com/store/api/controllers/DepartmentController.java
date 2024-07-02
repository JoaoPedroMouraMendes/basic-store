package com.store.api.controllers;

import com.store.api.domain.department.Department;
import com.store.api.domain.department.DepartmentRepository;
import com.store.api.domain.department.RequestDepartment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private DepartmentRepository repository;

    @GetMapping
    public ResponseEntity getAllDepartments() {
        var allDepartments = repository.findAll();
        return ResponseEntity.ok(allDepartments);
    }

    @PostMapping
    public ResponseEntity createDepartment(@RequestBody @Validated RequestDepartment data) {
        repository.save(new Department(data));
        return ResponseEntity.ok().build();
    }
}
