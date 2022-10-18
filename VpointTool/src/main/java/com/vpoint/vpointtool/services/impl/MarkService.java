package com.vpoint.vpointtool.services.impl;

import com.vpoint.vpointtool.exception.InputException;
import com.vpoint.vpointtool.models.entity.Item;
import com.vpoint.vpointtool.models.entity.Mark;
import com.vpoint.vpointtool.models.entity.User;
import com.vpoint.vpointtool.repositories.ItemRepository;
import com.vpoint.vpointtool.repositories.MarkRepository;
import com.vpoint.vpointtool.services.IItemService;
import com.vpoint.vpointtool.services.IMarkService;
import com.vpoint.vpointtool.services.IMarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class MarkService implements IMarkService {

    @Autowired
    private MarkRepository markRepository;
    
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private IItemService itemService;

    @Override
    public Mark save(Mark mark) {
        return markRepository.save(mark);
    }

    @Override
    public Mark findByItemAndDate(Item item, LocalDate date) {
        Optional<Mark> mark = markRepository.findByItemAndDate(item, date);
        if (mark.isPresent()) {
            return mark.get();
        } else {
            return null;
        }
    }

    @Override
    public float saveBestDepartment(long id, String value, User user, LocalDate date) {
        Item item = itemService.findById(id);
        Mark checkMark = markRepository.findByItemAndDateAndUserAndSign(item, date, user, value);
        if (checkMark != null) {
            return checkMark.getPoint();
        }
        float point = 0;
        if (value.equals("T")) {
            point = 10;
            Mark mark = new Mark();
            mark.setItem(item);
            mark.setUser(user);
            mark.setPoint(point);
            System.out.println(date);
            mark.setSign(value);
            mark.setDate(date);
            markRepository.save(mark);
        } else {
            List<Mark> marks = markRepository.findAllByItemAndDateAndUserAndSignIsNotIgnoreCase(item, date, user, "T");
            if (marks.size() == 0) {
                Mark mark = new Mark();
                point = bestDepartment(value);
                mark.setUser(user);
                mark.setItem(item);
                mark.setPoint(point);
                mark.setSign(value);
                mark.setDate(date);
                markRepository.save(mark);
            } else if (marks.size() == 1) {
                    if (convertBestDepartment(value) ==  convertBestDepartment(marks.get(0).getSign())) {
                        point = bestDepartment(value);
                        marks.get(0).setPoint(point);
                        marks.get(0).setSign(value);
                        markRepository.save(marks.get(0));
                    } else {
                        point = bestDepartment(value);
                        Mark mark1 = new Mark();
                        mark1.setUser(user);
                        mark1.setItem(item);
                        mark1.setPoint(point);
                        mark1.setSign(value);
                        mark1.setDate(date);
                        markRepository.save(mark1);
                    }
                } else {
                    for (Mark mark : marks) {
                        if (convertBestDepartment(value) ==  convertBestDepartment(mark.getSign())) {
                            point = bestDepartment(value);
                            mark.setPoint(point);
                            mark.setSign(value);
                            markRepository.save(mark);
                            break;
                        } else {
                            point = mark.getPoint();
                        }
                    }
                }
            }
        return point;
    }

    @Override
    public float saveDisciplineViolate(long id, float value, User user, LocalDate date) {
        Item item = itemService.findById(id);
        if (markRepository.findByItemAndDateAndUser(item, date, user) != null) {
            return 0;
        }
        Mark mark = new Mark();
        mark.setItem(item);
        mark.setUser(user);
        mark.setPoint(value);
        mark.setDate(date);
        markRepository.save(mark);
        return value;
    }

    @Override
    public float saveDisciplineBonus(long id, float value, User user, LocalDate date) {
        Item item = itemService.findById(id);
        if (markRepository.findByItemAndDateAndUser(item, date, user) != null) {
            return 0;
        }
        Mark mark = new Mark();
        mark.setItem(item);
        mark.setUser(user);
        mark.setPoint(value);
        mark.setDate(date);
        markRepository.save(mark);
        return value;
    }

    @Override
    public float saveLoveVmg(long id, float value, User user, LocalDate date) {
        Item item = itemService.findById(id);
        if (markRepository.findByItemAndDateAndUser(item, date, user) != null) {
            return 0;
        }
        Mark mark = new Mark();
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
        mark.setPoint(point);
        mark.setItem(item);
        mark.setUser(user);
        mark.setDate(date);
        markRepository.save(mark);
        return point;
    }

    @Override
    public float saveKPI(long id, float value, User user, LocalDate date) {
        float pointKPI;
        Mark markKPI = new Mark();
        Item item = itemService.findById(id);
        if (markRepository.findByItemAndDateAndUser(item, date, user) != null) {
            return 0;
        }
        if ( value < 70 ) {
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
        markKPI.setDate(date);
        markKPI.setPoint(pointKPI);
        markRepository.save(markKPI);
        return pointKPI;
    }

    @Override
    public float saveBCSDepartment(long id, float value, User user, LocalDate date) {
        Item item = itemService.findById(id);
        Mark mark = new Mark();
        if (markRepository.findByItemAndDateAndUser(item, date, user) != null) {
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
        mark.setPoint(point);
        mark.setDate(date);
        markRepository.save(mark);
        return point;
    }

    @Override
    public float saveJointActivities(long id, float value, User user, LocalDate date) {
        Item item = itemService.findById(id);
        if (markRepository.findByItemAndDateAndUser(item, date, user) != null) {
            return 0;
        }
        Mark mark = new Mark();
        mark.setUser(user);
        mark.setPoint(value);
        mark.setDate(date);
        mark.setItem(item);
        markRepository.save(mark);
        return mark.getPoint();
    }

    @Override
    public float saveTrain(long id, float value, User user, LocalDate date) {
        Item item = itemService.findById(id);
        Mark mark = new Mark();
        if (markRepository.findByItemAndDateAndUser(item, date, user) != null) {
            return 0;
        }
        float point = 0;
        if (4 <= value &&
                value < 4.5) {
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
        mark.setDate(date);
        markRepository.save(mark);
        return point;
    }

    @Override
    public float saveTrainStaff(long id, float value, User user, LocalDate date) {
        Item item = itemService.findById(id);
        Mark mark = new Mark();
        if (markRepository.findByItemAndDateAndUser(item, date, user) != null) {
            return 0;
        }
        float point;
        if (value < 90) {
            point = -1;
        } else if (90 < value && value < 100) {
            point = 0;
        } else if (100 < value && value <= 103) {
            point = 1;
        } else if (103 < value && value <= 105) {
            point = 1.5f;
        } else {
            point = 2;
        }
        mark.setPoint(point);
        mark.setUser(user);
        mark.setItem(item);
        mark.setDate(date);
        markRepository.save(mark);
        return point;
    }

    @Override
    public float saveTrainVmg(long id, float value, User user, LocalDate date) {
        float point = value;
        Item item = itemService.findById(id);
        if (markRepository.findByItemAndDateAndUser(item, date, user) != null) {
            return 0;
        }
        Mark mark = new Mark();
        mark.setItem(item);
        mark.setUser(user);
        mark.setDate(date);
        mark.setPoint(point);
        markRepository.save(mark);
        return point;
    }

    @Override
    public float saveImprove(long id, User user, LocalDate date) {
        Item item = itemService.findById(id);
        Mark mark = new Mark();
        if (markRepository.findByItemAndDateAndUser(item, date, user) != null) {
            return 0;
        }
        int currentPoint = 0;
        if (markRepository.getPointImprove(user, item, date.getYear()) != null) {
            currentPoint = markRepository.getPointImprove(user, item, date.getYear());
        }
        float point;
        if (currentPoint >= 35) {
            return 0;
        } else if (currentPoint + 10 >= 35) {
            point = 35 - currentPoint;
            mark.setPoint(point);
        } else {
            point = 10;
            mark.setPoint(point);
        }
        mark.setItem(item);
        mark.setUser(user);
        mark.setDate(date);
        markRepository.save(mark);
        return mark.getPoint();
    }

    @Override
    public float saveExcellentDepartmentYear(long id, User user, LocalDate date) {
        Item item = itemService.findById(id);
        Mark mark = new Mark();
        if (markRepository.findByItemAndDateAndUser(item, date, user) != null) {
            return 0;
        }
        mark.setPoint(20f);
        mark.setDate(date);
        mark.setUser(user);
        mark.setItem(item);
        markRepository.save(mark);
        return 20;
    }

    @Override
    public float saveExcellentDepartmentMonth(long id, User user, LocalDate date) {
        Item item = itemService.findById(id);
        Mark mark = new Mark();
        if (markRepository.findByItemAndDateAndUser(item, date, user) != null) {
            return 0;
        }
        mark.setPoint(10f);
        mark.setDate(date);
        mark.setUser(user);
        mark.setItem(item);
        markRepository.save(mark);
        return 10;
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

    private float bestDepartment(String value) {
        switch (value) {
            case "T":
            case "DCQ":
                return 10;
            case "Q":
            case "DCN":
                return 30;
            case "NSQ":
                return 40;
            case "N":
                return 60;
            case "NSN":
                return 100;
            default:
                throw new InputException("bestDepartment");
        }
    }

    @Override
    public List<Mark> getMarkByIdUser(Long idUser) {
        return markRepository.getMarkByIdUser(idUser);
    }
}
