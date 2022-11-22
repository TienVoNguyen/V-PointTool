package com.vpoint.vpointtool.services.impl;

import com.vpoint.vpointtool.exception.DataNotFoundException;
import com.vpoint.vpointtool.exception.InputException;
import com.vpoint.vpointtool.models.dto.*;
import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.Mark;
import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.payload.response.ReportAllMark;
import com.vpoint.vpointtool.payload.response.ReportResponse;
import com.vpoint.vpointtool.repositories.ItemRepository;
import com.vpoint.vpointtool.repositories.MarkRepository;
import com.vpoint.vpointtool.repositories.UserRepository;
import com.vpoint.vpointtool.services.IItemService;
import com.vpoint.vpointtool.services.IMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class MarkService implements IMarkService {
    @Autowired
    private MarkRepository markRepository;

    @Autowired
    private IItemService itemService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mark save(Mark mark) {
        return markRepository.save(mark);
    }

    @Override
    public void remove(Long id) {
        markRepository.deleteById(id);
    }

    @Override
    public List<PointSum> getPointSum(int year) {
        return markRepository.getPointSum(year);
    }

    @Override
    public List<Year> getYear() {
        return markRepository.getYear();
    }

    @Override
    public String getValue(Long id, User user, LocalDate date) {
        Item item = itemService.findById(id);
        Optional<Mark> mark = markRepository.findByItemAndDateAndUser(item, date, user);
        return mark.map(Mark::getValue).orElse(null);
    }

    @Override
    public float saveBestDepartmentMonth(long id, Boolean value, User user, LocalDate date) {
        Item item = itemRepository.findById(id);
        Optional<Mark> mark = markRepository.findByItemAndDateAndUser(item, date, user);
        Mark markUpdate = new Mark();
        if (mark.isPresent()) {
            markUpdate = mark.get();
            if (!value) {
                markRepository.delete(markUpdate);
                return -10;
            }
        }
        if (!value) {
            return 0;
        }
        markUpdate.setValue(String.valueOf(value));
        markUpdate.setItem(item);
        markUpdate.setDate(date);
        markUpdate.setUser(user);
        markUpdate.setPoint(10F);
        markUpdate.setSign("T");
        markRepository.save(markUpdate);
        return 10f;
    }

    @Override
    public float saveBestDepartmentYear(long id, String value, User user, LocalDate date) {
        Item item = itemRepository.findById(id);
        if (value.isEmpty()) {
            Optional<Mark> mark = markRepository.findByItemAndDateAndUser(item, date, user);
            mark.ifPresent(mark1 -> markRepository.delete(mark1));
            return 0;
        }
        float point = bestDepartmentYear(value);
        return bestDepartmentYearAndQuarter(item, point, value, user, date);
    }

    @Override
    public float saveBestDepartmentQuarter(long id, String value, User user, LocalDate date) {
        Item item = itemRepository.findById(id);
        if (value.isEmpty()) {
            Optional<Mark> mark = markRepository.findByItemAndDateAndUser(item, date, user);
            mark.ifPresent(mark1 -> markRepository.delete(mark1));
            return 0;
        }
        float point = bestDepartmentQuarter(value);
        return bestDepartmentYearAndQuarter(item, point, value, user, date);
    }


    private float bestDepartmentYearAndQuarter(Item item, float point, String value, User user, LocalDate date) {
        Optional<Mark> mark = markRepository.findByItemAndDateAndUser(item, date, user);
        Mark mark1 = new Mark();
        if (mark.isPresent()) {
            mark1 = mark.get();
            mark1.setPoint(point);
            mark1.setValue(value);
            mark1.setSign(value);
            markRepository.save(mark1);
            return point;
        }
        mark1.setItem(item);
        mark1.setPoint(point);
        mark1.setValue(value);
        mark1.setSign(value);
        mark1.setUser(user);
        mark1.setDate(date);
        markRepository.save(mark1);
        return point;
    }


    @Override
    public Mark findByItemAndDate(Item item, LocalDate date) {
        Optional<Mark> mark = markRepository.findByItemAndDate(item, date);
        return mark.orElse(null);
    }

    @Override
    public List<Mark> findMarkByUserAndDate(User user, LocalDate date) {
        List<Mark> marks = markRepository.findMarkByUserAndAndDate(user, date);
        if (marks.isEmpty()) {
            throw new DataNotFoundException("Mark by username: " + user.getFullName() + " date: " + date.toString());
        } else {
            return marks;
        }
    }

    @Override
    public float saveDisciplineViolate(long id, Float value, User user, LocalDate date) {
        System.out.println("saveDisciplineViolate");
        Item item = itemRepository.findById(id);
        Optional<Mark> markUpdate = markRepository.findByItemAndDateAndUser(item, date, user);
        Mark mark = new Mark();
        if (markUpdate.isPresent()) {
            mark = markUpdate.get();
            if (value == null) {
                markRepository.delete(mark);
                return 0;
            }
        }
        if (value == null) {
            return 0;
        }
        mark.setItem(item);
        mark.setUser(user);
        mark.setValue(String.valueOf(value));
        mark.setPoint(value);
        mark.setDate(date);
        markRepository.save(mark);
        return value;
    }

    @Override
    public float saveDisciplineBonus(long id, Float value, User user, LocalDate date) {
        Item item = itemRepository.findById(id);
        Optional<Mark> markUpdate = markRepository.findByItemAndDateAndUser(item, date, user);
        Mark mark = new Mark();
        if (markUpdate.isPresent()) {
            mark = markUpdate.get();
            if (value == null) {
                markRepository.delete(mark);
                return 0;
            }
        }
        if (value == null) {
            return 0;
        }
        mark.setItem(item);
        mark.setUser(user);
        mark.setPoint(value);
        mark.setValue(String.valueOf(value));
        mark.setDate(date);
        markRepository.save(mark);
        return value;
    }

    @Override
    public float saveLoveVmg(long id, Float value, User user, LocalDate date) {
        Item item = itemRepository.findById(id);
        Optional<Mark> markUpdate = markRepository.findByItemAndDateAndUser(item, date, user);
        Mark mark = new Mark();
        if (markUpdate.isPresent()) {
            mark = markUpdate.get();
            if (value == null) {
                markRepository.delete(mark);
                return 0;
            }
        }
        if (value == null) {
            return 0;
        }
        float point;
        if (value > 65 && value <= 75) {
            point = 5;
        } else if (75 < value && value <= 85) {
            point = 10;
        } else if (85 < value && value <= 100) {
            point = 20;
        } else {
            point = 0;
        }
        System.out.println(point);
        mark.setPoint(point);
        mark.setValue(String.valueOf(value));
        mark.setItem(item);
        mark.setUser(user);
        mark.setDate(date);
        markRepository.save(mark);
        return point;
    }

    @Override
    public float saveKPI(long id, Float value, User user, LocalDate date) {
        float pointKPI;
        Mark markKPI = new Mark();
        Item item = itemRepository.findById(id);
        Optional<Mark> mark = markRepository.findByItemAndDateAndUser(item, date, user);
        if (mark.isPresent()) {
            markKPI = mark.get();
            if (value == null) {
                markRepository.delete(markKPI);
                return 0;
            }
        }
        if (value == null) {
            return 0;
        }
        if (value < 70) {
            pointKPI = -25;
        } else if (70 <= value && value < 95) {
            pointKPI = (value - 70) * -1;
        } else if (value == 95) {
            pointKPI = 0;
        } else if (95 < value && value < 130) {
            pointKPI = value - 95;
        } else {
            pointKPI = 35;
        }
        markKPI.setUser(user);
        markKPI.setItem(item);
        markKPI.setValue(String.valueOf(value));
        markKPI.setDate(date);
        markKPI.setPoint(pointKPI);
        markRepository.save(markKPI);
        return pointKPI;
    }

    @Override
    public float saveBCSDepartment(long id, Float value, User user, LocalDate date) {

        Item item = itemRepository.findById(id);
        Mark mark = new Mark();
        Optional<Mark> mark1 = markRepository.findByItemAndDateAndUser(item, date, user);
        if (mark1.isPresent()) {
            mark = mark1.get();
            if (value == null) {
                markRepository.delete(mark);
                return 0;
            }
        }
        if (value == null) {
            return 0;
        }
        float point;
        if (value < 100) {
            point = 0;
        } else if (100 <= value && value < 105) {
            point = 2;
        } else if (105 <= value && value < 110) {
            point = 5;
        } else {
            point = 10;
        }
        mark.setItem(item);
        mark.setUser(user);
        mark.setValue(String.valueOf(value));
        mark.setPoint(point);
        mark.setDate(date);
        markRepository.save(mark);
        return point;
    }

    @Override
    public float saveJointActivities(long id, Float value, User user, LocalDate date) {
        Item item = itemRepository.findById(id);
        Mark mark = new Mark();
        Optional<Mark> mark1 = markRepository.findByItemAndDateAndUser(item, date, user);
        if (mark1.isPresent()) {
            mark = mark1.get();
            if (value == null) {
                markRepository.delete(mark);
                return 0;
            }
        }
        if (value == null) {
            return 0;
        }
        mark.setUser(user);
        mark.setPoint(value);
        mark.setValue(String.valueOf(value));
        mark.setDate(date);
        mark.setItem(item);
        markRepository.save(mark);
        return mark.getPoint();
    }

    @Override
    public float saveTrain(long id, Float value, User user, LocalDate date) {
        Item item = itemRepository.findById(id);
        Mark mark = new Mark();
        Optional<Mark> mark1 = markRepository.findByItemAndDateAndUser(item, date, user);
        if (mark1.isPresent()) {
            mark = mark1.get();
            if (value == null) {
                markRepository.delete(mark);
                return 0;
            }
        }
        if (value == null) {
            return 0;
        }
        float point = 0;
        if (value < 4) {
            point = 0;
        } else if (4 <= value && value < 4.5) {
            point = 2;
        } else if (4.5 <= value &&
                value < 5) {
            point = 4;
        } else {
            point = 6;
        }
        mark.setItem(item);
        mark.setPoint(point);
        mark.setUser(user);
        mark.setValue(String.valueOf(value));
        mark.setDate(date);
        markRepository.save(mark);
        return point;
    }

    @Override
    public float saveTrainStaff(long id, Float value, User user, LocalDate date) {
        Item item = itemRepository.findById(id);
        Mark mark = new Mark();
        Optional<Mark> mark1 = markRepository.findByItemAndDateAndUser(item, date, user);
        if (mark1.isPresent()) {
            mark = mark1.get();
            if (value == null) {
                markRepository.delete(mark);
                return 0;
            }
        }
        if (value == null) {
            return 0;
        }
        float point;
        if (value <= 90) {
            point = -1;
        } else if (90 < value && value < 100) {
            point = 0;
        } else if (100 <= value && value <= 103) {
            point = 1;
        } else if (103 < value && value <= 105) {
            point = 1.5f;
        } else {
            point = 2;
        }
        mark.setPoint(point);
        mark.setUser(user);
        mark.setItem(item);
        mark.setValue(String.valueOf(value));
        mark.setDate(date);
        markRepository.save(mark);
        return point;
    }

    @Override
    public float saveTrainVmg(long id, Float value, User user, LocalDate date) {
        Item item = itemRepository.findById(id);
        Mark mark = new Mark();
        Optional<Mark> mark1 = markRepository.findByItemAndDateAndUser(item, date, user);
        if (mark1.isPresent()) {
            mark = mark1.get();
            if (value == null) {
                markRepository.delete(mark);
            }
        }
        if (value == null) {
            return 0;
        }
        mark.setItem(item);
        mark.setUser(user);
        mark.setDate(date);
        mark.setValue(String.valueOf(value));
        mark.setPoint(value);
        markRepository.save(mark);
        return value;
    }

    @Override
    public float saveImprove(long id, Boolean value, User user, LocalDate date) {
        return 0;
    }

//    @Override
//    public float saveImprove(long id, Boolean value, User user, LocalDate date) {
//        Item item = itemRepository.findById(id);
//        Mark mark = new Mark();
//        Optional<Mark> mark1 = markRepository.findByItemAndDateAndUser(item, date, user);
//        if (mark1.isPresent()) {
//            mark = mark1.get();
//            float tmp = mark.getPoint();
//            if (!value) {
//                markRepository.delete(mark);
//                List<Mark> marks = markRepository.findMarksByUserAndItemInYear(item, user, date.getYear());
//                for (Mark m : marks) {
//                    if (tmp > 0 &&
//                            markRepository.getPointImprove(user, item, date.getYear()) < 35 &&
//                            m.getPoint() < 10) {
//                        if (m.getPoint() == 5) {
//                            tmp = tmp - 5;
//                            m.setPoint(10F);
//                        } else {
//                            m.setPoint(tmp);
//                        }
//                        markRepository.save(m);
//                    }
//                }
//                return 0;
//            }
//            return mark.getPoint();
//        } else {
//            if (!value) {
//                return 0;
//            }
//            int currentPoint = 0;
//            if (markRepository.getPointImprove(user, item, date.getYear()) != null) {
//                currentPoint = markRepository.getPointImprove(user, item, date.getYear());
//            }
//            float point;
//            if (currentPoint >= 35) {
//                point = 0;
//            } else if (currentPoint + 10 >= 35) {
//                point = 35 - currentPoint;
//            } else {
//                point = 10;
//            }
//            mark.setPoint(point);
//            mark.setItem(item);
//            mark.setUser(user);
//            mark.setDate(date);
//            mark.setValue(String.valueOf(true));
//            markRepository.save(mark);
//            return mark.getPoint();
//        }
//    }

    @Override
    public float saveExcellentDepartmentYear(long id, Boolean value, User user, LocalDate date) {
        Item item = itemRepository.findById(id);
        Mark mark = new Mark();
        Optional<Mark> mark1 = markRepository.findByItemAndDateAndUser(item, date, user);
        if (mark1.isPresent()) {
            mark = mark1.get();
            if (!value) {
                markRepository.delete(mark);
                return 0;
            }
        }
        if (!value) {
            return 0;
        }
        mark.setPoint(20f);
        mark.setDate(date);
        mark.setValue(String.valueOf(true));
        mark.setUser(user);
        mark.setItem(item);
        markRepository.save(mark);
        return 20;
    }

    @Override
    public float saveExcellentDepartmentMonth(long id, Boolean value, User user, LocalDate date) {
        Item item = itemRepository.findById(id);
        Mark mark = new Mark();
        Optional<Mark> mark1 = markRepository.findByItemAndDateAndUser(item, date, user);
        if (mark1.isPresent()) {
            mark = mark1.get();
            if (!value) {
                markRepository.delete(mark);
                return 0;
            }
        }
        if (!value) {
            return 0;
        }
        mark.setPoint(10f);
        mark.setDate(date);
        mark.setValue(String.valueOf(true));
        mark.setUser(user);
        mark.setItem(item);
        markRepository.save(mark);
        return 10;
    }

    @Override
    public Mark findByItemAndUserAndDate(Item item, User user, LocalDate date) {
        return markRepository.findByItemAndDateAndUser(item, date, user)
                .orElse(null);
    }

    private int convertBestDepartment(String value) {
        if (value.equals("DCQ") || value.equals("Q") || value.equals("NSQ")) {
            return 0;
        } else if (value.equals("DCN") || value.equals("N") || value.equals("NSN")) {
            return 1;
        } else {
            throw new InputException("BestDepartment");
        }
    }

    private float bestDepartmentQuarter(String value) {
        switch (value) {
            case "DCQ":
                return 10;
            case "Q":
                return 30;
            case "NSQ":
                return 40;
            default:
                throw new InputException("bestDepartmentQuarter: " + value);
        }
    }

    private float bestDepartmentYear(String value) {
        switch (value) {
            case "DCN":
                return 30;
            case "N":
                return 60;
            case "NSN":
                return 100;
            default:
                throw new InputException("bestDepartmentYear: " + value);
        }
    }

    @Override
    public List<UserYear> getDate(Long id) {
        return markRepository.getDate(id);
    }

    @Override
    public List<ResponseMark> getMarkByIdUser(Long idUser) {
        return markRepository.getMarkByIdUser(idUser);
    }

    @Override
    public List<ResponseMark> getMarkByIdUserAndYear(Long idUser, int year) {
        return markRepository.getMarkByIdUserAndYear(idUser, year);
    }

    @Override
    public List<Sum> getSum(int year) {
        return markRepository.getSum(year);
    }

    @Override
    public List<Mark> getMarkByTime(Long idUser, int Year, int Month) {
        return markRepository.getMarkByTime(idUser, Year, Month);
    }

    @Override
    public List<Integer> getSumByIdUser(Long idUser) {
        return markRepository.getSumByIdUser(idUser);
    }

    @Override
    public List<ReportAllMark> reportMark(int month, int year, String department) {
        List<ReportAllMark> reportAllMarks = new ArrayList<>();
        List<ReportResponse> reportResponses = markRepository.reportMark(month, year, department);
        List<User> users = userRepository.findAllByDepartment_NameAnd(department);
        List<User> list = new ArrayList<>();
        for (int i = 0; i < users.size(); i++) {
            User user = users.get(i);
            for(ReportResponse mark : reportResponses) {
                if (mark.getStaffId().equals(user.getStaffId())) {
                    ReportAllMark reportAllMark = new ReportAllMark();
                    reportAllMark.setFullName(mark.getFullName());
                    reportAllMark.setDepartment(mark.getDepartment());
                    reportAllMark.setStaffId(mark.getStaffId());
                    reportAllMark.setTotal(mark.getTotal());
                    reportAllMark.setMonth(mark.getMonth());
                    reportAllMark.setYear(mark.getYear());
                    User user1 = userRepository.findUserByStaffId(mark.getStaffId()).get();
                    List<Mark> marks = markRepository.findMarkByUserIdAndYearAndMonth(user1.getId(), mark.getYear(), mark.getMonth());
                    float excellentDepartAndStaff = 0;
                    float train = 0;
                    for (Mark mark1 : marks) {
                        if (mark1.getItem().getId() == 1) {
                            reportAllMark.setKpi(mark1.getPoint());
                        }
                        if (mark1.getItem().getId() == 2 || mark1.getItem().getId() == 9 || mark1.getItem().getId() == 10
                                || mark1.getItem().getId() == 16 || mark1.getItem().getId() == 17) {
                            excellentDepartAndStaff += mark1.getPoint();
                        }
                        if (mark1.getItem().getId() == 3) {
                            reportAllMark.setBsc(mark1.getPoint());
                        }
                        if (mark1.getItem().getId() == 4) {
                            reportAllMark.setJointActivities(mark1.getPoint());
                        }
                        if (mark1.getItem().getId() == 5 || mark1.getItem().getId() == 11 || mark1.getItem().getId() == 12) {
                            train += mark1.getPoint();
                        }
                        if (mark1.getItem().getId() == 6) {
                            reportAllMark.setImprove(mark1.getPoint());
                        }
                        if (mark1.getItem().getId() == 7) {
                            reportAllMark.setLoveVMG(mark1.getPoint());
                        }
                        if (mark1.getItem().getId() == 8) {
                            reportAllMark.setDiscipline(mark1.getPoint());
                        }
                        if (mark1.getItem().getId() == 13) {
                            reportAllMark.setBonus(mark1.getPoint());
                        }
                    }
                    reportAllMark.setTrain(train);
                    reportAllMark.setExcellentStaffAndDepartment(excellentDepartAndStaff);
                    reportAllMarks.add(reportAllMark);
                    list.add(user);
                }
            }

        }
        users.removeAll(list);
        users.forEach(user1 -> {
            ReportAllMark reportAllMark = new ReportAllMark();
            reportAllMark.setFullName(user1.getFullName());
            reportAllMark.setDepartment(user1.getDepartment().getName());
            reportAllMark.setStaffId(user1.getStaffId());
            reportAllMark.setMonth(month);
            reportAllMark.setYear(year);
            reportAllMarks.add(reportAllMark);
        });

        return reportAllMarks;
    }

    @Override
    public List<Integer> findAllYear() {
        return markRepository.findAllYear();
    }
}
