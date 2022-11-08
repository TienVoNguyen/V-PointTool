package com.vpoint.vpointtool.payload.response;

import lombok.Data;

import java.util.List;

@Data
public class ReportAllMark {
    private String staffId;
    private String fullName;
    private String department;
    private Float total;
    private Integer month;
    private Integer year;
    private Float kpi;
    private Float excellentStaffAndDepartment;
    private Float bsc;
    private Float jointActivities;
    private Float train;
    private Float improve;
    private Float loveVMG;
    private Float bonus;
    private Float discipline;
}
