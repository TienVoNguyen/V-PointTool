package com.vpoint.vpointtool.controller;

import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.exception.InputException;
import com.vpoint.vpointtool.models.dto.ResponseMark;
import com.vpoint.vpointtool.models.dto.UserYear;
import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.Mark;
import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.payload.request.AddMarkUser;
import com.vpoint.vpointtool.payload.request.MarkUserDate;
import com.vpoint.vpointtool.payload.response.MarkResponse;
import com.vpoint.vpointtool.payload.response.MessageResponse;
import com.vpoint.vpointtool.services.IItemService;
import com.vpoint.vpointtool.services.IMarkService;
import com.vpoint.vpointtool.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/mark")
@CrossOrigin("*")
public class MarkController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IItemService itemService;

    @Autowired
    private IMarkService markService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping("/test")
    public String index() {
        return "index";
    }

    @PostMapping("/add")
    @Transactional
    public ResponseEntity<?> addMarkUser(@Valid @RequestBody AddMarkUser markUser,
                                                    BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity
                    .badRequest()
                    .body(messageError(result));
        }
        MarkResponse markResponse = new MarkResponse();
        User user = userService.findByStaffId(markUser.getStaff_id());
        LocalDate localDate = LocalDate.of(markUser.getYear(), markUser.getMonth(), 1);

        if (markUser.getKpiID() != null) {
            System.out.println("KPI" + markUser.getKpi());
            float pointKpi = markService.saveKPI(markUser.getKpiID(), markUser.getKpi(), user, localDate);
            markResponse.setPointKPI(pointKpi);
        }
        if (markUser.getBestDepartmentMonthID() != null && markUser.getBestDepartmentMonth() != null) {
            float pointBestDepartment = markService.saveBestDepartmentMonth(
                    markUser.getBestDepartmentMonthID(), markUser.getBestDepartmentMonth(), user, localDate);
            markResponse.setPointBestDepartmentMonth(pointBestDepartment);
        }
        if (markUser.getBestDepartmentQuarter() != null && markUser.getBestDepartmentQuarter() != null) {
            float pointBestDepartment = markService.saveBestDepartmentQuarter(markUser.getBestDepartmentQuarterID(),
                    markUser.getBestDepartmentQuarter(), user, localDate);
            markResponse.setPointBestDepartmentQuarter(pointBestDepartment);
        }
        if (markUser.getBestDepartmentYearID() != null && markUser.getBestDepartmentYear() != null) {
            float pointBestDepartment = markService.saveBestDepartmentYear(markUser.getBestDepartmentYearID(),
                    markUser.getBestDepartmentYear(), user, localDate);
            markResponse.setPointBestDepartmentYear(pointBestDepartment);
        }
        if (markUser.getBcsDepartmentID() != null ) {
            float pointBCSDepartment = markService.saveBCSDepartment(markUser.getBcsDepartmentID(),
                    markUser.getBcsDepartment(), user, localDate);
            markResponse.setPointBCSDepartment(pointBCSDepartment);
        }
        if (markUser.getJointActivitiesID() != null ) {
            float pointJoint = markService.saveJointActivities(markUser.getJointActivitiesID(),
                    markUser.getJointActivities(), user, localDate);
            markResponse.setPointJointActivities(pointJoint);
        }
        if (markUser.getTrainID() != null ) {
            float pointTrain = markService.saveTrain(markUser.getTrainID(), markUser.getTrain(), user, localDate);
            markResponse.setPointTrain(pointTrain);
        }
        if (markUser.getTrainStaffID() != null ) {
            float pointTrainStaff = markService.saveTrainStaff(markUser.getTrainStaffID(),
                    markUser.getTrainStaff(), user, localDate);
            markResponse.setPointTrainStaff(pointTrainStaff);
        }
        if (markUser.getTrainVmgID() != null ) {
            float pointTrainVmg = markService.saveTrainVmg(markUser.getTrainVmgID(), markUser.getTrainVmg(), user,
                    localDate);
            markResponse.setPointTrainVmg(pointTrainVmg);
        }
        if (markUser.getLoveVmgID() != null ) {
            float pointLoveVmg = markService.saveLoveVmg(markUser.getLoveVmgID(), markUser.getLoveVmg(), user, localDate);
            markResponse.setPointLoveVmg(pointLoveVmg);
        }
        if (markUser.getDisciplineBonusID() != null ) {
            float pointDisciplineBonus = markService.saveDisciplineBonus(markUser.getDisciplineBonusID(),
                    markUser.getDisciplineBonus(), user, localDate);
            markResponse.setPointDisciplineBonus(pointDisciplineBonus);
        }
        if (markUser.getDisciplineViolateID() != null ) {
            float pointDisciplineViolate = markService.saveDisciplineViolate(markUser.getDisciplineViolateID(),
                    markUser.getDisciplineViolate(), user, localDate);
            markResponse.setPointDisciplineViolate(pointDisciplineViolate);
        }
        if (markUser.getImproveID() != null && markUser.getImprove() != null) {
            float pointImprove = markService.saveImprove(markUser.getImproveID(), markUser.getImprove(),user, localDate);
            markResponse.setPointImprove(pointImprove);
        }
        if (markUser.getExcellentDepartmentMonthID() != null && markUser.getExcellentDepartmentMonth() != null) {
            float pointExcellentMonth = markService.saveExcellentDepartmentMonth(
                    markUser.getExcellentDepartmentMonthID(), markUser.getExcellentDepartmentMonth(),user, localDate);
            markResponse.setPointExcellentDepartmentMonth(pointExcellentMonth);
        }
        if (markUser.getExcellentDepartmentYearID() != null && markUser.getExcellentDepartmentYear() != null) {
            float pointExcellentDepartmentYear = markService.saveExcellentDepartmentYear(
                    markUser.getExcellentDepartmentYearID(), markUser.getExcellentDepartmentYear(), user, localDate);
            markResponse.setPointExcellentDepartmentYear(pointExcellentDepartmentYear);
        }

        return new ResponseEntity<>(markResponse, HttpStatus.CREATED);
    }

    @GetMapping("/myVpoint/{idUser}")
    public ResponseEntity<List<ResponseMark>> getMarkByIdUser(@PathVariable Long idUser){
        List<ResponseMark> responseMark = markService.getMarkByIdUser(idUser);
        return new ResponseEntity<>(responseMark, HttpStatus.OK);
    }

    @GetMapping("/getYear/{idUser}")
    public ResponseEntity<List<UserYear>> getYear(@PathVariable Long idUser){
        List<UserYear> responseYear = markService.getDate(idUser);
        return new ResponseEntity<>(responseYear, HttpStatus.OK);
    }

    @GetMapping("/myVpointByYear/{idUser}")
    public ResponseEntity<List<ResponseMark>> getMarkByIdUserAndYear(@PathVariable Long idUser, @RequestParam("year") int year){
        List<ResponseMark> responseMark = markService.getMarkByIdUserAndYear(idUser, year);
        return new ResponseEntity<>(responseMark, HttpStatus.OK);
    }

    @GetMapping("/user/date")
    public ResponseEntity<?> getMarkUserDate(@RequestParam("userId") String id,
                                             @RequestParam("month") int month,
                                             @RequestParam("year") int year) {
        User user = userService.findById(Long.valueOf(id));
        LocalDate date = LocalDate.of(year, month, 1);
        return new ResponseEntity<>(setValue(user, date), HttpStatus.OK);
    }

    private AddMarkUser setValue(User user, LocalDate date) {
        AddMarkUser markUser = new AddMarkUser();
        String kpi = markService.getValue(markUser.getKpiID(), user, date);
        markUser.setStaff_id(user.getStaffId());
        if (kpi != null) {
            markUser.setKpi(Float.parseFloat(kpi));
        }
        String bestDepartment = markService.getValue(markUser.getBestDepartmentMonthID(), user, date);
        if (bestDepartment != null) {
            markUser.setBestDepartmentMonth(true);
        }
        markUser.setBestDepartmentQuarter(markService.getValue(markUser.getBestDepartmentQuarterID(), user, date));
        markUser.setBestDepartmentYear(markService.getValue(markUser.getBestDepartmentYearID(), user, date));
        markUser.setBestDepartmentYear(markService.getValue(markUser.getBestDepartmentYearID(), user, date));
        markUser.setExcellentDepartmentMonth(markService.getValue(
                markUser.getExcellentDepartmentMonthID(), user, date) != null);
        markUser.setExcellentDepartmentYear(
                markService.getValue(markUser.getExcellentDepartmentYearID(), user , date) != null);
        String bcs = markService.getValue(markUser.getBcsDepartmentID(), user, date);
        if (bcs != null ) {
            markUser.setBcsDepartment(Float.valueOf(bcs));
        }
//        markUser.setBcsDepartment(Float.valueOf(markService.getValue(markUser.getBcsDepartmentID(), user, date)));
        String join = markService.getValue(markUser.getJointActivitiesID(), user, date);
        if (join != null) {
            markUser.setJointActivities(Float.valueOf(join));
        }
        String train = markService.getValue(markUser.getTrainID(), user, date);
        if (train != null) {
            markUser.setTrain(Float.valueOf(train));
        }
        String trainStaff = markService.getValue(markUser.getTrainStaffID(), user, date);
        if (trainStaff != null) {
            markUser.setTrainStaff(Float.valueOf(trainStaff));
        }
        String trainVmg = markService.getValue(markUser.getTrainVmgID(), user, date);
        if (trainVmg != null) {
            markUser.setTrainVmg(Float.valueOf(trainVmg));
        }
        markUser.setImprove(markService.getValue(markUser.getImproveID(), user, date) != null);
        String love = markService.getValue(markUser.getLoveVmgID(), user, date);
        if (love != null) {
            markUser.setLoveVmg(Float.valueOf(love));
        }
        String bonus =  markService.getValue(markUser.getDisciplineBonusID(), user, date);
        if (bonus != null) {
            markUser.setDisciplineBonus(Float.valueOf(bonus));
        }
        String violate = markService.getValue(markUser.getDisciplineViolateID(), user, date);
        if (violate != null) {
            markUser.setDisciplineViolate(Float.valueOf(violate));
        }
        return markUser;
    }

    private List<MessageResponse> messageError(BindingResult result) {
        List<MessageResponse> messageResponses = new ArrayList<>();
        for (Object object : result.getAllErrors()) {
            if (object instanceof FieldError) {
                FieldError fieldError = (FieldError) object;
                String message = messageSource.getMessage(fieldError, null);
                messageResponses.add(new MessageResponse(message));
                System.out.println(message);
            }
        }
        return messageResponses;
    }



    @GetMapping("/{idUser}")
    public ResponseEntity<List<Mark>> getMarkByTime(@PathVariable Long idUser,
                                                    @RequestParam("year") int year,
                                                    @RequestParam("month") int month){

        return new ResponseEntity<>(markService.getMarkByTime(idUser, year, month), HttpStatus.OK);
    }
}
