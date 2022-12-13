package com.vpoint.vpointtool.payload.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MarkDecimal {
    private Long id;
    private Float start;
    private Float end;

    @NotNull(message = "Point cannot null")
    private Float point;
    private Float pointRule;
}
