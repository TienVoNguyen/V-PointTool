package com.vpoint.vpointtool.services.impl;

import com.vpoint.vpointtool.helper.ExcelHelper;
import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.services.IExcelService;
import com.vpoint.vpointtool.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.util.List;

@Service
public class ExcelService implements IExcelService {

    @Autowired
    private IUserService userService;

    @Override
    public ByteArrayInputStream exportUser() {
        List<User> users = userService.getAllUser();

        ByteArrayInputStream in = ExcelHelper.UsersToExcel(users);
        return in;
    }
}