package com.vpoint.vpointtool.payload.request;

import lombok.Data;

@Data
public class MarkDetail {
    private String staffId;
    private int year;
    private int month;
    private float totalPoint;
}
