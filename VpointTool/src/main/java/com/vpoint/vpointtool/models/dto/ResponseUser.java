package com.vpoint.vpointtool.models.dto;

import com.vpoint.vpointtool.models.entity.Department;

public interface ResponseUser {
    Long getId();
    String getStaffId();
    String getFullname();
    Integer getPoint();
    String getDepartment();
}
