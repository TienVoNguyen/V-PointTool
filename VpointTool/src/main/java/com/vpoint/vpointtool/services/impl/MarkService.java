package com.vpoint.vpointtool.services.impl;

import com.vpoint.vpointtool.models.dto.ResponseMark;
import com.vpoint.vpointtool.models.dto.Sum;
import com.vpoint.vpointtool.models.dto.UserYear;
import com.vpoint.vpointtool.models.entity.Mark;
import com.vpoint.vpointtool.repositories.MarkRepository;
import com.vpoint.vpointtool.services.IMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarkService implements IMarkService {

    @Autowired
    private MarkRepository markRepository;

    @Override
    public Mark save(Mark mark) {
        return markRepository.save(mark);
    }

    @Override
    public List<UserYear> getDate(Long id) {
        return markRepository.getDate(id);
    }

    @Override
    public List<ResponseMark> getMarkByIdUser(Long idUser) {
        return markRepository.getMarkByIdUser(idUser);
    }

    @Override
    public List<ResponseMark> getMarkByIdUserAndYear(Long idUser, int year) {
        return markRepository.getMarkByIdUserAndYear(idUser, year);
    }

    @Override
    public List<Sum> getSum() {
        return markRepository.getSum();
    }

    @Override
    public List<Mark> getMarkByTime(Long idUser, int Year, int Month) {
        return markRepository.getMarkByTime(idUser, Year, Month);
    }

    @Override
    public List<Integer> getSumByIdUser(Long idUser) {
        return markRepository.getSumByIdUser(idUser);
    }
}
