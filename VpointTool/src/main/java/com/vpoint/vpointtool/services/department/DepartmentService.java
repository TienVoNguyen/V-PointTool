package com.vpoint.vpointtool.services.department;

import com.vpoint.vpointtool.models.entity.Department;
import com.vpoint.vpointtool.payload.response.ResponseCate;
import com.vpoint.vpointtool.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return departmentRepository.findById(id);
    }

    @Override
    public Department save(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public void remove(Long id) {
    }

    @Override
    public List<ResponseCate> listDpm() {
        return departmentRepository.listDpm();
    }

    @Override
    public Page<Department> findAll(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    @Override
    public List<Department> getAll() {
        return departmentRepository.getAll();
    }

    @Override
    public List<Department> findByDpmName(String name) {
        return departmentRepository.findByDpmName(name);
    }
}
