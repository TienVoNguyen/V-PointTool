package com.vpoint.vpointtool.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vpoint.vpointtool.models.login.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@Entity
@Table(name = "mark")
@Data
@Transactional
@AllArgsConstructor
public class Mark extends BaseEntity{

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "symbol_id")
    private Symbol symbol;

    @Column(name = "point")
    private Float point;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "value")
    private String value;

    @Column(name = "sign")
    private String sign;
}
