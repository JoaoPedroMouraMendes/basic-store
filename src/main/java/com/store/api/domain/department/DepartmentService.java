package com.store.api.domain.department;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public void createDepartment(PostRequestDepartment data) {
        departmentRepository.save(new Department(data));
    }

    public Department updateDepartment(UUID id, PutRequestDepartment data) {
        // Busca o objeto no banco de dados
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        // Valida se o objeto foi encontrado
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            department.setName(data.name());
            return department;
        }
        return null;
    }

    public boolean deleteDepartment(UUID id) {
        // Busca o objeto no banco de dados
        Optional<Department> optionalDepartment = departmentRepository.findById(id);
        if (optionalDepartment.isPresent()) {
            Department department = optionalDepartment.get();
            departmentRepository.delete(department);
            return true;
        }
        return false;
    }
}
