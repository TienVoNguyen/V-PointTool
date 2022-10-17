package com.vpoint.vpointtool.models.login;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vpoint.vpointtool.models.entity.BaseEntity;
import com.vpoint.vpointtool.models.entity.Department;
import com.vpoint.vpointtool.models.entity.Mark;
import com.vpoint.vpointtool.models.login.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@Table(name = "user")
public class User extends BaseEntity {

    @Size(max = 120)
    @Column(name = "staff_id", length = 120)
    private String staffId;

    @Size(max = 50)
    @Column(name = "full_name", length = 50)
    private String fullName;

    @Size(max = 150)
    @Column(name = "password", length = 150)
    private String password;

    @Size(max = 150)
    @Column(name = "email", length = 150)
    private String email;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Department department;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
//    @JsonIgnore
    private Set<Role> roleSet;


    @OneToMany(mappedBy = "user")
//    @JsonIgnore
    private List<Mark> marks;

    public User(String staffId, String fullName, String password, String email, Department department, Set<Role> roleSet) {
        this.staffId = staffId;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.department = department;
        this.roleSet = roleSet;
    }

    public User(Long id, String createBy, String createDate, Date modifiedDate, String modifiedBy, String staffId, String fullName, String password, String email, Department department, Set<Role> roleSet) {
        super(id, createBy, createDate, modifiedDate, modifiedBy);
        this.staffId = staffId;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.department = department;
        this.roleSet = roleSet;
    }

    public User() {
    }

    public User(Long id, String createBy, String createDate, Date modifiedDate, String modifiedBy, String staffId, String fullName, String password, String email, Department department, Set<Role> roleSet, List<Mark> marks) {
        super(id, createBy, createDate, modifiedDate, modifiedBy);
        this.staffId = staffId;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.department = department;
        this.roleSet = roleSet;
        this.marks = marks;
    }

    public User(String staffId, String fullName, String password, String email, Department department, Set<Role> roleSet, List<Mark> marks) {
        this.staffId = staffId;
        this.fullName = fullName;
        this.password = password;
        this.email = email;
        this.department = department;
        this.roleSet = roleSet;
        this.marks = marks;
    }
}
