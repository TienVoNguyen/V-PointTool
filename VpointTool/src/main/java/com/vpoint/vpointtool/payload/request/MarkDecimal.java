package com.vpoint.vpointtool.payload.request;

import lombok.Data;

@Data
public class MarkDecimal {
    private Long id;
    private Float start;
    private Float end;
    private Float point;
    private Float pointRule;
}
