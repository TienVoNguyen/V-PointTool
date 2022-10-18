package com.vpoint.vpointtool.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkResponse {
    private float pointKPI;

    private float pointBestDepartment;

    private float pointBCSDepartment;

    private float pointJointActivities;

    private float pointTrain;

    private float pointTrainStaff;

    private float pointTrainVmg;

//    private float pointImprove;

    private float pointLoveVmg;

    private float pointDisciplineBonus;

    private float pointDisciplineViolate;
}
