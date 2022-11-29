package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.dto.ResponseUser;
import com.vpoint.vpointtool.models.entity.Department;
import com.vpoint.vpointtool.payload.response.ResponseCate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
 @Query(nativeQuery = true, value = "select department_id as id, count(user()) as quantity\n" +
         "from department join user u on department.id = u.department_id group by department_id")
 List<ResponseCate> listDpm();

 Page<Department> findAll(Pageable pageable);

 @Query(nativeQuery = true, value = "select * from department")
 List<Department> getAll();

 @Query(nativeQuery = true, value = "select * from department where name like '%' ? '%'")
 List<Department> findByDpmName(String name);
}
