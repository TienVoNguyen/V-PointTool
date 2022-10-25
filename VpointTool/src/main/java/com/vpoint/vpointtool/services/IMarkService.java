package com.vpoint.vpointtool.services;

import com.vpoint.vpointtool.models.dto.ResponseMark;
import com.vpoint.vpointtool.models.dto.Sum;
import com.vpoint.vpointtool.models.dto.UserYear;
import com.vpoint.vpointtool.models.entity.Mark;

import java.util.List;

public interface IMarkService {
    Mark save(Mark mark);

    List<UserYear> getDate(Long id);

    List<ResponseMark> getMarkByIdUser(Long idUser);

    List<ResponseMark> getMarkByIdUserAndYear(Long idUser, int year);

    List<Sum> getSum();

    List<Mark> getMarkByTime(Long idUser, int Year, int Month);

    List<Integer> getSumByIdUser(Long idUser);
}
