package com.vpoint.vpointtool.services;

import com.vpoint.vpointtool.models.dto.*;
import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.Mark;
import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.payload.response.ReportAllMark;
import com.vpoint.vpointtool.payload.response.ReportResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IMarkService {
    Mark save(Mark mark);

    void remove(Long id);

    List<PointSum> getPointSum(int year);

    List<Year> getYear();

    List<ResponseMark> getMarkByIdUser(Long idUser);

    Mark findByItemAndDate(Item item, LocalDate date);

    List<Mark> findMarkByUserAndDate(User user, LocalDate date);

//    float saveBestDepartment(long id, String value, User user, LocalDate date);

    float saveDisciplineViolate(long id, Float value, User user, LocalDate date);

    float saveDisciplineBonus(long id, Float value, User user, LocalDate date);

    float saveLoveVmg(long id, Float value, User user, LocalDate date);

    float saveKPI(long id, Float value, User user, LocalDate date);

    float saveBCSDepartment(long id, Float value, User user, LocalDate date);

    float saveJointActivities(long id, Float value, User user, LocalDate date);

    float saveTrain(long id, Float value, User user, LocalDate date);

    float saveTrainStaff(long id, Float value, User user, LocalDate date);

    float saveTrainVmg(long id, Float value, User user, LocalDate date);

    float saveImprove(long id, Boolean value,User user, LocalDate date);

    float saveExcellentDepartmentYear(long id, Boolean value,User user, LocalDate date);

    float saveExcellentDepartmentMonth(long id, Boolean value,User user, LocalDate date);

    Mark findByItemAndUserAndDate(Item item, User user, LocalDate date);

    String getValue(Long id, User user, LocalDate date);

    float saveBestDepartmentMonth(long id, Boolean value , User user, LocalDate date);

    float saveBestDepartmentYear(long id, String value , User user, LocalDate date);

    float saveBestDepartmentQuarter(long id, String value , User user, LocalDate date);

    List<UserYear> getDate(Long id);

    List<ResponseMark> getMarkByIdUserAndYear(Long idUser, int year);

    List<Sum> getSum(int year);

    List<Mark> getMarkByTime(Long idUser, int Year, int Month);

    List<Integer> getSumByIdUser(Long idUser);

    List<ReportAllMark> reportMark(int month, int year, String department);

    List<Integer> findAllYear();
}
