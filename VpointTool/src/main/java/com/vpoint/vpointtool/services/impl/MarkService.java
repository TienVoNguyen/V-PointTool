package com.vpoint.vpointtool.services.impl;

import com.vpoint.vpointtool.models.entity.Mark;
import com.vpoint.vpointtool.repositories.MarkRepository;
import com.vpoint.vpointtool.services.IMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarkService implements IMarkService {

    @Autowired
    private MarkRepository markRepository;

    @Override
    public Mark save(Mark mark) {
        return markRepository.save(mark);
    }
}
