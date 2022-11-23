package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.dto.*;
import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.Mark;
import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.payload.response.ReportResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MarkRepository extends JpaRepository<Mark, Long> {
    Optional<Mark> findByItemAndDate(Item item, LocalDate date);

    Optional<Mark> findByItemAndDateAndUser(Item item, LocalDate date, User user);

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

    @Query(nativeQuery = true, value = "select sum(point) as sum, mark.date as date from mark where user_id = ? and year(date) = (year(current_date)) group by month(date);")
    List<ResponseMark> getMarkByIdUser(Long idUser);

    List<Mark> findMarkByUserAndAndDate(User user, LocalDate date);

    @Query("select m from Mark m where m.item = :item and m.user = :user and year(m.date) = :year")
    List<Mark> findMarksByUserAndItemInYear(
            @Param("item") Item item, @Param("user") User user, @Param("year") Integer year);

    @Query(nativeQuery = true, value = "select sum(point) as sum, mark.date as date from mark where user_id = ?1 and year(date) = ?2 group by month(date) order by date")
    List<ResponseMark> getMarkByIdUserAndYear(Long idUser, int year);

    @Query(nativeQuery = true, value = "select date from mark where user_id = ? group by year(date) order by date;")
    List<UserYear> getDate(Long id);

    @Query(nativeQuery = true, value = "select u.staff_id as staffId, sum(point) as sum from mark join user u on u.id = mark.user_id where Year(date) = ? group by user_id;")
    List<Sum> getSum(int year);

    @Query(nativeQuery = true, value = "select * from mark where user_id = ? and Year(date) = ? and MONTH(date) = ?")
    List<Mark> getMarkByTime(Long idUser, int Year, int Month);

    @Query(nativeQuery = true, value = "select sum(point) from mark where user_id = ? and year(date) = (year(current_date)) group by month(date)")
    List<Integer> getSumByIdUser(Long idUser);

    @Query(nativeQuery = true, value = "select staff_id as staffId, sum(point) as sum from user join mark m on user.id = m.user_id where year(date) = ?;")
    List<PointSum> getPointSum(int year);


    @Query(nativeQuery = true, value = "select date from mark group by year(date) order by year(date);")
    List<Year> getYear();

    @Query(value = "select u.staff_id as staffId, u.full_name as fullName, d.name as department, year(m.date) as year, month(m.date) as month, sum(m.point) as total\n" +
            "from user u join department d on d.id = u.department_id left join mark m on u.id = m.user_id\n" +
            "where month(m.date)=:month and year(m.date)=:year and d.name like %:department% group by u.staff_id, u.full_name, d.name, year(m.date), month(m.date);", nativeQuery = true)
    List<ReportResponse> reportMark(@Param("month") int month, @Param("year") int year, @Param("department") String department);

    @Query(value = "select * from mark where user_id = :userId and year(mark.date) = :year and month(mark.date) = :month", nativeQuery = true)
    List<Mark> findMarkByUserIdAndYearAndMonth(@Param("userId") Long userId, @Param("year") Integer month, @Param("month") Integer year);

    @Query(value = "select year(date) as year from mark group by year", nativeQuery = true)
    List<Integer> findAllYear();
}
