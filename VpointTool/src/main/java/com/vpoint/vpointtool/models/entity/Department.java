package com.vpoint.vpointtool.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vpoint.vpointtool.models.login.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "department")
public class Department extends BaseEntity{
    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "department")
    private List<User> users;

    public Department(Long id, String createBy, String createDate, Date modifiedDate, String modifiedBy, String name) {
        super(id, createBy, createDate, modifiedDate, modifiedBy);
        this.name = name;
    }

    public Department(String name) {
        this.name = name;
    }
}
