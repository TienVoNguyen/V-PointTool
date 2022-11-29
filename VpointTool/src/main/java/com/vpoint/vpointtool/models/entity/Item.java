package com.vpoint.vpointtool.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "item")
public class Item extends BaseEntity{
    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @ManyToOne
    @JsonIgnore
    private Category category;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private List<Mark> marks;

    private Type type;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private Set<Symbol> symbols;

    private PointRule pointRule;

    private Float start;
    private Float end;
}
