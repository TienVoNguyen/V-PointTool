package com.vpoint.vpointtool.services;

import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.Mark;
import com.vpoint.vpointtool.models.login.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IMarkService {
    Mark save(Mark mark);

    List<Mark> getMarkByIdUser(Long idUser);

    Mark findByItemAndDate(Item item, LocalDate date);

    List<Mark> findMarkByUserAndDate(User user, LocalDate date);

    float saveBestDepartment(long id, String value, User user, LocalDate date);

    float saveDisciplineViolate(long id, float value, User user, LocalDate date);

    float saveDisciplineBonus(long id, float value, User user, LocalDate date);

    float saveLoveVmg(long id, float value, User user, LocalDate date);

    float saveKPI(long id, float value, User user, LocalDate date);

    float saveBCSDepartment(long id, float value, User user, LocalDate date);

    float saveJointActivities(long id, float value, User user, LocalDate date);

    float saveTrain(long id, float value, User user, LocalDate date);

    float saveTrainStaff(long id, float value, User user, LocalDate date);

    float saveTrainVmg(long id, float value, User user, LocalDate date);

    float saveImprove(long id, User user, LocalDate date);

    float saveExcellentDepartmentYear(long id, User user, LocalDate date);

    float saveExcellentDepartmentMonth(long id, User user, LocalDate date);

    Mark findByItemAndUserAndDate(Item item, User user, LocalDate date);

    String getValue(Long id, User user, LocalDate date);
}
