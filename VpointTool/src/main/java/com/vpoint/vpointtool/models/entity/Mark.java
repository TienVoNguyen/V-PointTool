package com.vpoint.vpointtool.models.entity;

import javax.persistence.*;

@Entity
@Table(name = "mark")
public class Mark extends BaseEntity{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "symbol_id")
    private Symbol symbol;

    @Column(name = "point")
    private Float point;


}