package com.vpoint.vpointtool.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Size(max = 120)
    @Column(name = "staff_id", length = 120)
    private String staffId;

    @Size(max = 50)
    @Column(name = "full_name", length = 50)
    private String fullName;

    @Size(max = 150)
    @Column(name = "password", length = 150)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Mark> marks;

    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<Role> roles = new HashSet<>();
}