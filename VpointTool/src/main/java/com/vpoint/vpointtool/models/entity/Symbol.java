package com.vpoint.vpointtool.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Symbol {
    @Id
    private Long id;

    private String description;

    private Float start;

    private Float end;

    private String sign;

    private Calculation calculation;

    private Float point;

    private Boolean compare;

    @OneToMany(mappedBy = "item")
    @JsonIgnore
    private List<Mark> marks;

    @ManyToOne
    @JoinColumn(name = "item_id")
    @JsonIgnore
    private Item item;
}
