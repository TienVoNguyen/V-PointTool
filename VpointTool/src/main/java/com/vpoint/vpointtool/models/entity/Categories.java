package com.vpoint.vpointtool.models.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Categories extends BaseEntity{
    private String name;
    private double point;
    @ManyToOne
    private Category category;
}
