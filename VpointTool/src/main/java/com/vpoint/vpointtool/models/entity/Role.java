package com.vpoint.vpointtool.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role extends BaseEntity{
    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;
}