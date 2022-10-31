package com.vpoint.vpointtool.payload.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MarkUserDate {
    @NotNull(message = "UserId cannot null")
    private Long userId;

    @NotNull(message = "month cannot null")
    private Integer month;

    @NotNull(message = "year cannot null")
    private Integer year;
}
