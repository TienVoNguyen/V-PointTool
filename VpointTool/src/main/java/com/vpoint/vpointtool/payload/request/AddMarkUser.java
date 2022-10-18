package com.vpoint.vpointtool.payload.request;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class AddMarkUser {

    private Float kpi;
    private Long kpiID;

    private String bestDepartment;
    private Long bestDepartmentID;

    private Long excellentDepartmentMonthID;
    private boolean excellentDepartmentMonth;

    private Long excellentDepartmentYearID;
    private boolean excellentDepartmentYear;

    private Float bcsDepartment;
    private Long bcsDepartmentID;

    @DecimalMin(value = "-0.5")
    @Max(4)
    private Float jointActivities;
    private Long jointActivitiesID;

    private Float train;
    private Long trainID;

    private Float trainStaff;
    private Long trainStaffID;

    private Float trainVmg;
    private Long trainVmgID;

    private boolean improve;
    private Long improveID;

    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float loveVmg;
    private Long loveVmgID;

    private Float disciplineBonus;
    private Long disciplineBonusID;

    private Float disciplineViolate;
    private Long disciplineViolateID;

    @Min(1)
    @Max(12)
    private int month;

    private int year;
}
