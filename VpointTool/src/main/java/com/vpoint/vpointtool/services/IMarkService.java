package com.vpoint.vpointtool.services;

import com.vpoint.vpointtool.models.entity.Mark;

import java.util.List;

public interface IMarkService {
    Mark save(Mark mark);

    List<Mark> getMarkByIdUser(Long idUser);
}
