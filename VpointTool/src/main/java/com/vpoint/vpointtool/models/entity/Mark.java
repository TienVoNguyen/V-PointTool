package com.vpoint.vpointtool.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vpoint.vpointtool.models.login.User;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Entity
@Table(name = "mark")
@Data
public class Mark extends BaseEntity{
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @Column(name = "point")
    private Float point;

    @Column(name = "date")
    private LocalDate date;

}
