package com.vpoint.vpointtool.models.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Category extends BaseEntity{
    private String name;
    @OneToMany(mappedBy = "category")
    private Set<Categories> categories;
}
