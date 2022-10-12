package com.vpoint.vpointtool.models.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

/**
 * A DTO for the {@link com.vpoint.vpointtool.models.entity.User} entity
 */
@Data
public class UserDto implements Serializable {
    private final Long id;
    @NotBlank
    private final String username;
    @NotBlank
    private final String password;
    @NotBlank
    @Size(max = 30)
    private final String fullName;
    @NotBlank
    @Email
    private final String email;
    private final Long departmentId;
    private final Set<Long> roleIds;
}