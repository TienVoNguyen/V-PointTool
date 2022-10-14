package com.vpoint.vpointtool.models.login;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vpoint.vpointtool.models.entity.BaseEntity;
import com.vpoint.vpointtool.models.entity.Department;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class User extends BaseEntity {
    @NotBlank
    @Column(length = 20, unique = true, nullable = false)
    private String username;

    @NotBlank
    @Column(length = 130, nullable = false)
    private String password;

    @NotBlank
    @Size(max = 30)
    @Column(length = 100)
    private String fullName;

    @NotBlank
    @Email
    private String email;

    @ManyToOne
    @JsonIgnore
    private Department department;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @JsonIgnore
    private Set<Role> roleSet;
}
