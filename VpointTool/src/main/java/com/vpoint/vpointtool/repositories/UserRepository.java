package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.dto.ResponseUser;
import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.payload.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByStaffId(String staff_id);
    Optional<User> findById(Long id);

    Page<User> findAll(Pageable pageable);

    @Query(nativeQuery = true, value = "select * from user where full_name like '%'  ?  '%' order by full_name;")
    List<User> listUser(String fullName);

    @Query(nativeQuery = true, value = "select * from user where department_id = ?;")
    List<User> listUserByCate(int idCate);

    @Query(nativeQuery = true, value = "select user.id as id,user.staff_id as staffId, user.full_name as fullname, sum(m.point) as point,\n" +
            "  d.name as department from user left join mark m on user.id = m.user_id join department d on d.id = user.department_id where\n" +
            "year(date) = ?1 or m.date IS NULL group by full_name order by  full_name", countQuery = "select count(id) from(select user.id as" +
            " id,user.staff_id as staffId, user.full_name as fullname, sum(m.point) as point,\n" +
            "  d.name as department from user left join mark m on user.id = m.user_id join department d on d.id = user.department_id where\n" +
            "year(date) = ?1 or m.date IS NULL group by full_name order by  full_name) where year(date) = ?1")
    Page<ResponseUser> listUserByYear(int year, Pageable pageable);

    @Query(value = "select * from user join department d on d.id = user.department_id where d.name like %:department%", nativeQuery = true)
    List<User> findAllByDepartment_NameAnd(String department);
}
