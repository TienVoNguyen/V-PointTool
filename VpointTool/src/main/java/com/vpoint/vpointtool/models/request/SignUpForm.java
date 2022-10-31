package com.vpoint.vpointtool.models.request;

import com.vpoint.vpointtool.models.entity.Department;
import com.vpoint.vpointtool.models.entity.Mark;
import com.vpoint.vpointtool.models.login.Role;
import lombok.*;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SignUpForm {

    private String staffId;

    private String fullname;

    private String password;

    private String confirmPassword;

    private String email;

    private Department department;

    private Set<Role> role;

}
