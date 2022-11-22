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
import com.vpoint.vpointtool.payload.response.ReportResponse;
import com.vpoint.vpointtool.services.IItemService;
import com.vpoint.vpointtool.services.IMarkService;
import com.vpoint.vpointtool.services.IUserService;
import com.vpoint.vpointtool.services.impl.MarkV2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@RestController
@RequestMapping(value = "/api/mark")
@CrossOrigin("*")
public class MarkController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IMarkService markService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private MarkV2Service markV2Service;

    @GetMapping("/test")
    public String index() {
        return "index";
    }

    @PostMapping("/add")
//    @PreAuthorize("hasAuthority('ADMIN')")
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
        markResponse.setStaff_id(user.getStaffId());
        markResponse.setDepartment(user.getDepartment().getName());
        markResponse.setFullName(user.getFullName());
        markResponse.setId(user.getId());

        LocalDate localDate = LocalDate.of(markUser.getYear(), markUser.getMonth(), 1);

        if (markUser.getKpiID() != null) {
            float pointKpi = markV2Service.saveDecimal(markUser.getKpiID(),markUser.getKpi(), user , localDate);
            markResponse.setPointKPI(pointKpi);
        }
        if (markUser.getBestDepartmentMonthID() != null) {
            float pointBestDepartment = markV2Service.saveBoolean(
                    markUser.getBestDepartmentMonthID(), markUser.getBestDepartmentMonth(), user, localDate);
            markResponse.setPointBestDepartmentMonth(pointBestDepartment);
        }
        if (markUser.getImproveYearId() != null) {
            float pointImproveYear = markV2Service.savePoint(
                    markUser.getImproveYearId(), markUser.getImproveYear(),user, localDate);
            markResponse.setPointImproveYear(pointImproveYear);
        }
        if (markUser.getBestDepartmentQuarterID() != null) {
            float pointBestDepartment = markV2Service.saveText(markUser.getBestDepartmentQuarterID(),
                    markUser.getBestDepartmentQuarter(), user, localDate);
            markResponse.setPointBestDepartmentQuarter(pointBestDepartment);
        }
        if (markUser.getBestDepartmentYearID() != null) {
            float pointBestDepartment = markV2Service.saveText(markUser.getBestDepartmentYearID(),
                    markUser.getBestDepartmentYear(), user, localDate);
            markResponse.setPointBestDepartmentYear(pointBestDepartment);
        }
        if (markUser.getBcsDepartmentID() != null ) {
            float pointBCSDepartment = markV2Service.saveDecimal(markUser.getBcsDepartmentID(),
                    markUser.getBcsDepartment(), user, localDate);
            markResponse.setPointBCSDepartment(pointBCSDepartment);
        }
        if (markUser.getJointActivitiesID() != null ) {
            float pointJoint = markV2Service.savePoint(markUser.getJointActivitiesID(),
                    markUser.getJointActivities(), user, localDate);
            markResponse.setPointJointActivities(pointJoint);
        }
        if (markUser.getTrainID() != null ) {
            float pointTrain = markV2Service.saveDecimal(markUser.getTrainID(), markUser.getTrain(), user, localDate);
            markResponse.setPointTrain(pointTrain);
        }
        if (markUser.getTrainStaffID() != null ) {
            float pointTrainStaff = markV2Service.saveDecimal(markUser.getTrainStaffID(),
                    markUser.getTrainStaff(), user, localDate);
            markResponse.setPointTrainStaff(pointTrainStaff);
        }
        if (markUser.getTrainVmgID() != null ) {
            float pointTrainVmg = markV2Service.savePoint(markUser.getTrainVmgID(), markUser.getTrainVmg(), user,
                    localDate);
            markResponse.setPointTrainVmg(pointTrainVmg);
        }
        if (markUser.getLoveVmgID() != null ) {
            float pointLoveVmg = markV2Service.saveDecimal(markUser.getLoveVmgID(), markUser.getLoveVmg(), user, localDate);
            markResponse.setPointLoveVmg(pointLoveVmg);
        }
        if (markUser.getDisciplineBonusID() != null ) {
            float pointDisciplineBonus = markV2Service.savePoint(markUser.getDisciplineBonusID(),
                    markUser.getDisciplineBonus(), user, localDate);
            markResponse.setPointDisciplineBonus(pointDisciplineBonus);
        }
        if (markUser.getDisciplineViolateID() != null ) {
            float pointDisciplineViolate = markV2Service.savePoint(markUser.getDisciplineViolateID(),
                    markUser.getDisciplineViolate(), user, localDate);
            markResponse.setPointDisciplineViolate(pointDisciplineViolate);
        }
        if (markUser.getImproveID() != null) {
            float pointImprove = markV2Service.saveBoolean(markUser.getImproveID(), markUser.getImprove(),user, localDate);
            markResponse.setPointImprove(pointImprove);
        }
        if (markUser.getExcellentDepartmentMonthID() != null) {
            float pointExcellentMonth = markV2Service.saveBoolean(
                    markUser.getExcellentDepartmentMonthID(), markUser.getExcellentDepartmentMonth(),user, localDate);
            markResponse.setPointExcellentDepartmentMonth(pointExcellentMonth);
        }
        if (markUser.getExcellentDepartmentYearID() != null) {
            float pointExcellentDepartmentYear = markV2Service.saveBoolean(
                    markUser.getExcellentDepartmentYearID(), markUser.getExcellentDepartmentYear(), user, localDate);
            markResponse.setPointExcellentDepartmentYear(pointExcellentDepartmentYear);
        }

        return new ResponseEntity<>(markResponse, HttpStatus.CREATED);
    }

    @GetMapping("/myVpoint/{idUser}")
    public ResponseEntity<List<ResponseMark>> getMarkByIdUser(@PathVariable Long idUser, @RequestParam("year") int year){
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

    @GetMapping("reportmark")
    public ResponseEntity<?> getReportMark(@RequestParam("month") Optional<Integer> month, @RequestParam("year") Optional<Integer> year
            , @RequestParam("department") Optional<String> department) {
        if (month.isPresent() && year.isPresent() ) {
            return new ResponseEntity<>(markService.reportMark(month.get(), year.get(), department.get()), HttpStatus.OK);
        }else {
            int year1 = Calendar.getInstance().get(Calendar.YEAR);
            int month1 = Calendar.getInstance().get(Calendar.MONTH);
            return new ResponseEntity<>(markService.reportMark(month1, year1, ""), HttpStatus.OK);
        }
    }

    @GetMapping("getallyear")
    public ResponseEntity<?> getAllYear() {
        return new ResponseEntity<>(markService.findAllYear(), HttpStatus.OK);
    }
}
