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

        if (markUser.getKpiID() != null && markUser.getKpi() != null) {
            float pointKpi = markService.saveKPI(markUser.getKpiID(), markUser.getKpi(), user, localDate);
            markResponse.setPointKPI(pointKpi);
        }
        if (markUser.getBestDepartmentID() != null && markUser.getBestDepartment() != null) {
            float pointBestDepartment = markService.saveBestDepartment(markUser.getBestDepartmentID(),
                    markUser.getBestDepartment(), user, localDate);
            markResponse.setPointBestDepartment(pointBestDepartment);
        }
        if (markUser.getBcsDepartmentID() != null && markUser.getBcsDepartment() != null) {
            float pointBCSDepartment = markService.saveBCSDepartment(markUser.getBcsDepartmentID(),
                    markUser.getBcsDepartment(), user, localDate);
            markResponse.setPointBCSDepartment(pointBCSDepartment);
        }
        if (markUser.getJointActivitiesID() != null && markUser.getJointActivities() != null) {
            float pointJoint = markService.saveJointActivities(markUser.getJointActivitiesID(),
                    markUser.getJointActivities(), user, localDate);
            markResponse.setPointJointActivities(pointJoint);
        }
        if (markUser.getTrainID() != null && markUser.getTrain() != null) {
            float pointTrain = markService.saveTrain(markUser.getTrainID(), markUser.getTrain(), user, localDate);
            markResponse.setPointTrain(pointTrain);
        }
        if (markUser.getTrainStaffID() != null && markUser.getTrainStaff() != null) {
            float pointTrainStaff = markService.saveTrainStaff(markUser.getTrainStaffID(),
                    markUser.getTrainStaff(), user, localDate);
            markResponse.setPointTrainStaff(pointTrainStaff);
        }
        if (markUser.getTrainVmgID() != null && markUser.getTrainVmg() != null) {
            float pointTrainVmg = markService.saveTrainVmg(markUser.getTrainVmgID(), markUser.getTrainVmg(), user,
                    localDate);
            markResponse.setPointTrainVmg(pointTrainVmg);
        }
        if (markUser.getLoveVmgID() != null && markUser.getLoveVmg() != null) {
            float pointLoveVmg = markService.saveLoveVmg(markUser.getLoveVmgID(), markUser.getLoveVmg(), user, localDate);
            markResponse.setPointLoveVmg(pointLoveVmg);
        }
        if (markUser.getDisciplineBonusID() != null && markUser.getDisciplineBonus() != null) {
            float pointDisciplineBonus = markService.saveDisciplineBonus(markUser.getDisciplineBonusID(),
                    markUser.getDisciplineBonus(), user, localDate);
            markResponse.setPointDisciplineBonus(pointDisciplineBonus);
        }
        if (markUser.getDisciplineViolateID() != null && markUser.getDisciplineViolate() != null) {
            float pointDisciplineViolate = markService.saveDisciplineViolate(markUser.getDisciplineViolateID(),
                    markUser.getDisciplineViolate(), user, localDate);
            markResponse.setPointDisciplineViolate(pointDisciplineViolate);
        }
        if (markUser.getImproveID() != null && markUser.isImprove()) {
            float pointImprove = markService.saveImprove(markUser.getImproveID(), user, localDate);
            markResponse.setPointImprove(pointImprove);
        }
        if (markUser.getExcellentDepartmentMonthID() != null && markUser.isExcellentDepartmentMonth()) {
            float pointExcellentMonth = markService.saveExcellentDepartmentMonth(
                    markUser.getExcellentDepartmentMonthID(), user, localDate);
            markResponse.setPointExcellentDepartmentMonth(pointExcellentMonth);
        }
        if (markUser.getExcellentDepartmentYearID() != null && markUser.isExcellentDepartmentYear()) {
            float pointExcellentDepartmentYear = markService.saveExcellentDepartmentYear(
                    markUser.getExcellentDepartmentYearID() , user, localDate);
            markResponse.setPointExcellentDepartmentYear(pointExcellentDepartmentYear);
        }

        return new ResponseEntity<>(markResponse, HttpStatus.CREATED);
    }

}
