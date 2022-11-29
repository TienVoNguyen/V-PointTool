package com.vpoint.vpointtool.payload.response;

import com.vpoint.vpointtool.models.entity.PointRule;
import com.vpoint.vpointtool.models.entity.Type;
import lombok.Data;

@Data
public class Rule {
    private Long id;
    private String name;
    private Float start;
    private Float end;
    private Type type;
    private PointRule pointRule;
}
