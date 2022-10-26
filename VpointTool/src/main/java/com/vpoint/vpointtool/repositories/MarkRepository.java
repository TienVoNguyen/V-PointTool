package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.dto.ResponseMark;
import com.vpoint.vpointtool.models.dto.Sum;
import com.vpoint.vpointtool.models.dto.UserYear;
import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.Mark;
import com.vpoint.vpointtool.models.login.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
@Repository


public interface MarkRepository extends JpaRepository<Mark, Long> {
    Optional<Mark> findByItemAndDate(Item item, LocalDate date);

    Mark findByItemAndDateAndUser(Item item, LocalDate date, User user);

    Mark findByItemAndDateAndUserAndSignIsNot(Item item, LocalDate date, User user, String sign);

    List<Mark> findAllByItemAndDateAndUserAndSignIsNotIgnoreCase(Item item, LocalDate date, User user, String sign);

    boolean existsByItemAndDateAndUserAndSign(Item item, LocalDate date, User user, String sign);

    Mark findByItemAndDateAndUserAndSign(Item item, LocalDate date, User user, String sign);

    @Query("select sum(m.point) as point " +
            "from Mark m " +
            "where m.item = :item " +
            "and m.user = :user " +
            "and year(m.date) = :year " +
            "group by year(m.date)")
    Integer getPointImprove(@Param("user") User user, @Param("item") Item item, @Param("year") int year);

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
