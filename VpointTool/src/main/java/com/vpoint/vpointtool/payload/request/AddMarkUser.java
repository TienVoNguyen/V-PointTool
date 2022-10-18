package com.vpoint.vpointtool.payload.request;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class AddMarkUser {

    private String staff_id;

//    ID: 1
    private Float kpi;
    private Long kpiID;

//    ID: 2
    private Long bestDepartmentID;
    private String bestDepartmentMonth;
    private String bestDepartmentQuarter;
    private String bestDepartmentYear;

//    ID: 9
    private Long excellentDepartmentMonthID;
    private boolean excellentDepartmentMonth;

//    ID: 10
    private Long excellentDepartmentYearID;
    private boolean excellentDepartmentYear;

//    ID: 3
    private Float bcsDepartment;
    private Long bcsDepartmentID;

//    ID: 4
    @DecimalMin(value = "-0.5")
    @Max(4)
    private Float jointActivities;
    private Long jointActivitiesID;

//    ID: 5
    private Float train;
    private Long trainID;

//    ID: 11
    private Float trainStaff;
    private Long trainStaffID;

//    ID: 12
    @Max(50)
    @Min(0)
    private Float trainVmg;
    private Long trainVmgID;

//    ID: 6
    private boolean improve;
    private Long improveID;

//    ID: 7
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float loveVmg;
    private Long loveVmgID;

//    ID: 13
    private Float disciplineBonus;
    private Long disciplineBonusID;

//    ID: 8
    private Float disciplineViolate;
    private Long disciplineViolateID;

    @Min(1)
    @Max(12)
    private int month;

    private int year;
}
