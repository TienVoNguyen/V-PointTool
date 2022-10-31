package com.vpoint.vpointtool.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class UserProfile {
    private Long id;
    private String department;
    private String name;
    private String staff_id;
    private String mail;
    private String gender;
    private String phone;
}
