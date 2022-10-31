package com.vpoint.vpointtool.models.login;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.vpoint.vpointtool.models.entity.BaseEntity;
import com.vpoint.vpointtool.models.login.User;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Role extends BaseEntity {
    @Column(length = 20)
    private String name;

}
