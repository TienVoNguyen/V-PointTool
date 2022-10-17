package com.vpoint.vpointtool.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "symbol")
@Data
public class Symbol extends BaseEntity{
    @Size(max = 50)
    @Column(name = "name", length = 50)
    private String name;

    @Lob
    @Column(name = "description")
    private String description;

    @Column(name = "point")
    private Float point;

    @Column(name = "start")
    private Float start;

    @Column(name = "end")
    private Float end;

    @Column(name = "comparison")
    private Boolean comparison;

    @OneToMany(mappedBy = "symbol")
    @JsonIgnore
    private List<Mark> marks;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private Item item;
}