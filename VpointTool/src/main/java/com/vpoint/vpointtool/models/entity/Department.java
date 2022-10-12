package com.vpoint.vpointtool.models.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Department extends BaseEntity{
    private String name;

    @OneToMany(mappedBy = "department")
    private List<User> users;
}
