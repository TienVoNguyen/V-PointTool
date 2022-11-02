package com.vpoint.vpointtool.payload.request;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class AddMarkUser {

    private String staff_id;

    private String department;

    private String fullName;

    //    ID: 1
    private Float kpi;
    private Long kpiID = 1L;

    //    ID: 2
    private Long bestDepartmentMonthID = 2L;
    private Boolean bestDepartmentMonth;

    //    ID: 16
    private Long bestDepartmentQuarterID = 16L;
    private String bestDepartmentQuarter;

    //    ID: 17
    private Long bestDepartmentYearID = 17L;
    private String bestDepartmentYear;

    //    ID: 9
    private Long excellentDepartmentMonthID = 9L;
    private Boolean excellentDepartmentMonth;

    //    ID: 10
    private Long excellentDepartmentYearID = 10L;
    private Boolean excellentDepartmentYear;

    //    ID: 3
    private Float bcsDepartment;
    private Long bcsDepartmentID = 3L;

    //    ID: 4
    @DecimalMin(value = "-0.5")
    @Max(4)
    private Float jointActivities;
    private Long jointActivitiesID = 4L;

    //    ID: 5
    private Float train;
    private Long trainID = 5L;

    //    ID: 11
    private Float trainStaff;
    private Long trainStaffID = 11L;

    //    ID: 12
    @Max(50)
    @Min(0)
    private Float trainVmg;
    private Long trainVmgID = 12L;

    //    ID: 6
    private Boolean improve;
    private Long improveID = 6L;

    //    ID: 7
    @DecimalMin(value = "0")
    @DecimalMax(value = "100")
    private Float loveVmg;
    private Long loveVmgID = 7L;

    //    ID: 13
    private Float disciplineBonus;
    private Long disciplineBonusID = 13L;

    //    ID: 8
    private Float disciplineViolate;
    private Long disciplineViolateID = 8L;

    @Min(1)
    @Max(12)
    @NotNull(message = "Tháng không để trống")
    private Integer month;

    @NotNull(message = "Năm không để trống")
    private Integer year;
}
