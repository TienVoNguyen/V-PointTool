package com.vpoint.vpointtool.services.department;

import com.vpoint.vpointtool.models.entity.Department;
import com.vpoint.vpointtool.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService implements IDepartmentService {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<Department> findAll() {
        return departmentRepository.findAll();
    }

    @Override
    public Optional<Department> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Department save(Department department) {
        return null;
    }

    @Override
    public void remove(Long id) {

    }
}
