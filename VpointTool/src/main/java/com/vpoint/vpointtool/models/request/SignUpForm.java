package com.vpoint.vpointtool.models.request;

import com.vpoint.vpointtool.models.entity.Department;
import com.vpoint.vpointtool.models.login.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
    private String username;

    private String password;

    private String confirmPassword;

    private String email;

    private Department department;

    private String fullName;

    private Set<Role> roles;

}
