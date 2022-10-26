package com.vpoint.vpointtool.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdminChangePass {
    private String newPassword;
    private String confirmNewPass;
}
