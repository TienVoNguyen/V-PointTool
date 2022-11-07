package com.vpoint.vpointtool.payload.response;

import lombok.Data;

public interface ReportResponse {
    String getFullName();
    String getDepartment();
    String getStaffId();
    int getYear();
    int getMonth();
    float getTotal();
}
