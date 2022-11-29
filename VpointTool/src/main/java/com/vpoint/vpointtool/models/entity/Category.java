package com.vpoint.vpointtool.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "category")
public class Category extends BaseEntity{

    @Size(max = 100)
    @Column(name = "name", length = 100)
    private String name;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Item> items;

    public Category(Long id, String createBy, String createDate, Date modifiedDate, String modifiedBy, String name) {
        super(id, createBy, createDate, modifiedDate, modifiedBy);
        this.name = name;
    }

    public Category(String name) {
        this.name = name;
    }
}
