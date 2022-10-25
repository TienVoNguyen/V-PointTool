package com.vpoint.vpointtool.services;

import com.vpoint.vpointtool.models.dto.ResponseMark;
import com.vpoint.vpointtool.models.dto.Sum;
import com.vpoint.vpointtool.models.entity.Mark;

import java.util.List;

public interface IMarkService {
    Mark save(Mark mark);

    List<ResponseMark> getMarkByIdUser(Long idUser);

    List<Sum> getSum();

    List<Mark> getMarkByTime(Long idUser, int Year, int Month);

    List<Integer> getSumByIdUser(Long idUser);
}
