package com.vpoint.vpointtool.services.department;

import com.vpoint.vpointtool.models.entity.Department;
import com.vpoint.vpointtool.payload.response.ResponseCate;
import com.vpoint.vpointtool.services.IGeneralService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IDepartmentService extends IGeneralService<Department> {
    List<ResponseCate> listDpm();

    Page<Department> findAll(Pageable pageable);

    List<Department> getAll();

    List<Department> findByDpmName(String name);
}
