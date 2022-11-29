package com.vpoint.vpointtool.payload.response;

import lombok.Data;

public interface ReportResponse {
    String getFullName();
    String getDepartment();
    String getStaffId();
    Integer getYear();
    Integer getMonth();
    Float getTotal();
}
