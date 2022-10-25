package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.dto.ResponseMark;
import com.vpoint.vpointtool.models.dto.Sum;
import com.vpoint.vpointtool.models.dto.UserYear;
import com.vpoint.vpointtool.models.entity.Mark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
@Repository
public interface MarkRepository extends JpaRepository<Mark, Long> {


    @Query(nativeQuery = true, value = "select sum(point) as sum, mark.date as date from mark where user_id = ? and year(date) = (year(current_date)) group by month(date)")
    List<ResponseMark> getMarkByIdUser(Long idUser);

    @Query(nativeQuery = true, value = "select sum(point) as sum, mark.date as date from mark where user_id = ? and year(date) = ? group by month(date)")
    List<ResponseMark> getMarkByIdUserAndYear(Long idUser, int year);

    @Query(nativeQuery = true, value = "select date from mark where user_id = ? group by year(date) order by date;")
    List<UserYear> getDate(Long id);

    @Query(nativeQuery = true, value = "select user_id as id, sum(point) as sum from mark where Year(date) = (year(current_date)) group by user_id;")
    List<Sum> getSum();

    @Query(nativeQuery = true, value = "select * from mark where user_id = ? and Year(date) = ? and MONTH(date) = ?")
    List<Mark> getMarkByTime(Long idUser, int Year, int Month);

    @Query(nativeQuery = true, value = "select sum(point) from mark where user_id = ? and year(date) = (year(current_date)) group by month(date)")
    List<Integer> getSumByIdUser(Long idUser);




}
