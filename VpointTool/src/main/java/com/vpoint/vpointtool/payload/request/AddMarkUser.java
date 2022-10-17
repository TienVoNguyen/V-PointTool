package com.vpoint.vpointtool.payload.request;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class AddMarkUser {

    private float kpi;
    private Long kpiID;

    private String bestDepartment;
    private Long bestDepartmentID;

    private float bcsDepartment;
    private Long bcsDepartmentID;

    @DecimalMin(value = "-0.5")
    @Max(4)
    private float jointActivities;
    private Long jointActivitiesID;

    private float train;
    private Long trainID;

    private float trainStaff;
    private Long trainStaffID;

    private float trainVmg;
    private Long trainVmgID;

//    private float improve;
//    private Long improveID;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private float loveVmg;
    private Long loveVmgID;

    private float disciplineBonus;
    private Long disciplineBonusID;

    private float disciplineViolate;
    private Long disciplineViolateID;

    private int month;
    private int year;
}
