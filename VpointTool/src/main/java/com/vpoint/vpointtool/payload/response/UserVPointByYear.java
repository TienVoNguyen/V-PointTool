package com.vpoint.vpointtool.payload.response;

import com.vpoint.vpointtool.models.dto.ResponseUser;

import java.util.List;

public class UserVPointByYear {
    int totalPage;
    List<ResponseUser> userList;

    public UserVPointByYear(int totalPage, List<ResponseUser> userList) {
        this.totalPage = totalPage;
        this.userList = userList;
    }
}
