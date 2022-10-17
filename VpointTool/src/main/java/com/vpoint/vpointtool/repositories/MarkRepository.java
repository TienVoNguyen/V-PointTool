package com.vpoint.vpointtool.repositories;

import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.Mark;
import com.vpoint.vpointtool.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MarkRepository extends JpaRepository<Mark, Long> {
    Optional<Mark> findByItemAndDate(Item item, LocalDate date);

    Mark findByItemAndDateAndUser(Item item, LocalDate date, User user);

    Mark findByItemAndDateAndUserAndSignIsNot(Item item, LocalDate date, User user, String sign);

    List<Mark> findAllByItemAndDateAndUserAndSignIsNotIgnoreCase(Item item, LocalDate date, User user, String sign);

    boolean existsByItemAndDateAndUserAndSign(Item item, LocalDate date, User user, String sign);

    Mark findByItemAndDateAndUserAndSign(Item item, LocalDate date, User user, String sign);
}