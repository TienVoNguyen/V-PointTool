package com.vpoint.vpointtool.controller;

import com.vpoint.vpointtool.exception.InputException;
import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.Mark;
import com.vpoint.vpointtool.models.entity.Symbol;
import com.vpoint.vpointtool.models.entity.User;
import com.vpoint.vpointtool.payload.request.AddMarkUser;
import com.vpoint.vpointtool.payload.response.MarkResponse;
import com.vpoint.vpointtool.payload.response.MessageResponse;
import com.vpoint.vpointtool.services.IItemService;
import com.vpoint.vpointtool.services.IMarkService;
import com.vpoint.vpointtool.services.ISymbolService;
import com.vpoint.vpointtool.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/api/mark")
public class MarkController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IItemService itemService;

    @Autowired
    private ISymbolService symbolService;

    @Autowired
    private IMarkService markService;

    @GetMapping("/test")
    public String index() {
        return "index";
    }

    @PostMapping("/{id}")
    @Transactional
    public ResponseEntity<?> addMarkUser(@Valid @RequestBody AddMarkUser markUser,
                                                    BindingResult result,
                                                    @PathVariable("id") long id) {
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Input"));
        }
        MarkResponse markResponse = new MarkResponse();
        User user = userService.findById(id);
        LocalDate localDate = LocalDate.of(markUser.getYear(), markUser.getMonth(), 1);

        if (markUser.getKpiID() != null) {
            float pointKpi = markService.saveKPI(markUser.getKpiID(), markUser.getKpi(), user, localDate);
            markResponse.setPointKPI(pointKpi);
        }
        if (markUser.getBestDepartmentID() != null) {
            float pointBestDepartment = markService.saveBestDepartment(markUser.getBestDepartmentID(),
                    markUser.getBestDepartment(), user, localDate);
            markResponse.setPointBestDepartment(pointBestDepartment);
        }
        if (markUser.getBcsDepartmentID() != null) {
            float pointBCSDepartment = markService.saveBCSDepartment(markUser.getBcsDepartmentID(),
                    markUser.getBcsDepartment(), user, localDate);
            markResponse.setPointBCSDepartment(pointBCSDepartment);
        }
        if (markUser.getJointActivitiesID() != null) {
            float pointJoint = markService.saveJointActivities(markUser.getJointActivitiesID(),
                    markUser.getJointActivities(), user, localDate);
            markResponse.setPointJointActivities(pointJoint);
        }
        if (markUser.getTrainID() != null) {
            float pointTrain = markService.saveTrain(markUser.getTrainID(), markUser.getTrain(), user, localDate);
            markResponse.setPointTrain(pointTrain);
        }
        if (markUser.getTrainStaffID() != null) {
            float pointTrainStaff = markService.saveTrainStaff(markUser.getTrainStaffID(),
                    markUser.getTrainStaff(), user, localDate);
            markResponse.setPointTrainStaff(pointTrainStaff);
        }
        if (markUser.getTrainVmgID() != null) {
            float pointTrainVmg = markService.saveTrainVmg(markUser.getTrainVmgID(), markUser.getTrainVmg(), user,
                    localDate);
            markResponse.setPointTrainVmg(pointTrainVmg);
        }
        if (markUser.getLoveVmgID() != null) {
            float pointLoveVmg = markService.saveLoveVmg(markUser.getLoveVmgID(), markUser.getLoveVmg(), user, localDate);
            markResponse.setPointLoveVmg(pointLoveVmg);
        }
        if (markUser.getDisciplineBonusID() != null) {
            float pointDisciplineBonus = markService.saveDisciplineBonus(markUser.getDisciplineBonusID(),
                    markUser.getDisciplineBonus(), user, localDate);
            markResponse.setPointDisciplineBonus(pointDisciplineBonus);
        }
        if (markUser.getDisciplineViolateID() != null) {
            float pointDisciplineViolate = markService.saveDisciplineViolate(markUser.getDisciplineViolateID(),
                    markUser.getDisciplineViolate(), user, localDate);
            markResponse.setPointDisciplineViolate(pointDisciplineViolate);
        }

        return new ResponseEntity<>(markResponse, HttpStatus.CREATED);
    }

//    private float markDisciplineViolate(long id, float value, User user, LocalDate date) {
//        Item item = itemService.findById(id);
//        Mark mark = new Mark();
//        mark.setItem(item);
//        mark.setUser(user);
//        mark.setPoint(value);
//        mark.setDate(date);
//        markService.save(mark);
//        return value;
//    }
//
//    private float markDisciplineBonus(long id, float value, User user, LocalDate date) {
//        Item item = itemService.findById(id);
//        Mark mark = new Mark();
//        mark.setItem(item);
//        mark.setUser(user);
//        mark.setPoint(value);
//        mark.setDate(date);
//        markService.save(mark);
//        return value;
//    }
//    private float markLoveVmg(long id, float value, User user, LocalDate date) {
//        Item item = itemService.findById(id);
//        Mark mark = new Mark();
//        float point;
//        if (value > 65 && value <= 75) {
//            point = 5;
//        } else if (75 < value && value <= 85) {
//            point = 10;
//        } else if (85 < value && value <= 100) {
//            point = 20;
//        } else {
//            point = 0;
//        }
//        mark.setPoint(point);
//        mark.setItem(item);
//        mark.setUser(user);
//        mark.setDate(date);
//        markService.save(mark);
//        return point;
//    }
//
//    private float markKPI(long id, float value, User user, LocalDate date) {
//
//        float pointKPI;
//        Mark markKPI = new Mark();
//        Item item = itemService.findById(id);
//        if ( value < 70 ) {
//            pointKPI = -25;
//        } else if (70 <= value && value < 95) {
//            pointKPI = (value - 70) * -1;
//        } else if (value == 95) {
//            pointKPI = 0;
//        } else if (95 < value && value < 130) {
//            pointKPI = value - 95;
//        } else {
//            pointKPI = 35;
//        }
//        markKPI.setUser(user);
//        markKPI.setItem(item);
//        markKPI.setDate(date);
//        markKPI.setPoint(pointKPI);
//        markService.save(markKPI);
//        return pointKPI;
//    }
//
//    private float markBestDepartment(long id, String value, User user, LocalDate date) {
//        return 0;
////        Item item = itemService.findById(id);
////        float point = 0;
////        if (value.equals("T")) {
////            point = 10;
////            Mark mark = new Mark();
////            mark.setUser(user);
////            mark.setItem(item);
////            mark.setDate(date);
////            mark.setKey(value);
////            mark.setPoint(point);
////            markService.save(mark);
////            return point;
////        } else {
////            Mark mark = markService.findByItemAndDate(item, date);
////            if (mark.getUser() == null) {
////                mark.setUser(user);
////                mark.setItem(item);
////                mark.setDate(date);
////            }
////            if (value.equals("DCQ") || value.equals("Q") || value.equals("NSQ")) {
////                if (mark.getKey() != null && (mark.getKey().equals("DCQ") || mark.getKey().equals("Q") || mark.getKey().equals("NSQ"))) {
////                }
////            } else if (value.equals("DCN") || value.equals("N") || value.equals("NSN")) {
////            } else {
////                throw new InputException("BestDepartment");
////            }
////        }
//
//
////        Mark mark = new Mark();
//
////        if (value.equals("DCQ")) {
////            point = 10;
////        } else if (value.equals("Q")) {
////            point = 30;
////        } else if (value.equals("NSQ")) {
////            point = 40;
////        } else if (value.equals("DCN")) {
////            point = 30;
////        } else if (value.equals("N")) {
////            point = 30;
////        } else if (value.equals("NSN")) {
////            point = 100;
////        } else {
////            throw new InputException("BestDepartment");
////        }
////
////        if (mark.getPoint() == null || mark.getPoint() < point ) {
////            mark.setPoint(point);
////        }
////        markService.save(mark);
////        return point;
//    }
//
//    private float markBCSDepartment(long id, float value, User user, LocalDate date) {
//        Item item = itemService.findById(id);
//        Mark mark = new Mark();
//        System.out.println(value);
//        float point;
//        if (value < 100) {
//            point = 0;
//        } else if (100 <= value && value < 105) {
//            point = 2;
//        } else if (105 <= value && value < 110) {
//            point = 5;
//        } else {
//            point = 10;
//        }
//        mark.setItem(item);
//        mark.setUser(user);
//        mark.setPoint(point);
//        mark.setDate(date);
//        markService.save(mark);
//        return point;
//    }
//
//    private float markJointActivities(long id, float value, User user, LocalDate date) {
//        Item item = itemService.findById(id);
//        Mark mark = new Mark();
//        mark.setUser(user);
//        mark.setPoint(value);
//        mark.setDate(date);
//        mark.setItem(item);
//        markService.save(mark);
//        return mark.getPoint();
//    }
//
//    private float markTrain(long id, float value, User user, LocalDate date) {
//        Item item = itemService.findById(id);
//        Mark mark = new Mark();
//        float point = 0;
//        if (4 <= value &&
//            value < 4.5) {
//            point = 2;
//        } else if (4.5 <= value &&
//                value < 5) {
//            point = 4;
//        } else {
//            point = 6;
//        }
//        mark.setItem(item);
//        mark.setPoint(point);
//        mark.setUser(user);
//        mark.setDate(date);
//        markService.save(mark);
//        return point;
//    }
//
//    private float markTrainStaff(long id, float value, User user, LocalDate date) {
//        Item item = itemService.findById(id);
//        Mark mark = new Mark();
//        float point;
//        if (value < 90) {
//            point = -1;
//        } else if (90 < value && value < 100) {
//            point = 0;
//        } else if (100 < value && value <= 103) {
//            point = 1;
//        } else if (103 < value && value <= 105) {
//            point = 1.5f;
//        } else {
//            point = 2;
//        }
//        mark.setPoint(point);
//        mark.setUser(user);
//        mark.setItem(item);
//        mark.setDate(date);
//        markService.save(mark);
//        return point;
//    }
//
//    private float markTrainVmg(long id, float value, User user, LocalDate date) {
//        float point = value;
//        Item item = itemService.findById(id);
//        Mark mark = new Mark();
//        mark.setItem(item);
//        mark.setUser(user);
//        mark.setDate(date);
//        mark.setPoint(point);
//        markService.save(mark);
//        return point;
//    }
}
