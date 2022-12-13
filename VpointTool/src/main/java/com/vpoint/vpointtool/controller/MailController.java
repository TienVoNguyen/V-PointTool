package com.vpoint.vpointtool.controller;

import com.vpoint.vpointtool.models.beans.MailInfo;
import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.payload.request.MarkDetail;
import com.vpoint.vpointtool.repositories.UserRepository;
import com.vpoint.vpointtool.services.MailerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@CrossOrigin("*")
public class MailController {
    private final MailerService mailer;

    private  final UserRepository userRepository;

    public MailController(MailerService mailer, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.mailer = mailer;
    }

    @PostMapping("/mailer")
    public ResponseEntity<?> sendMail(@RequestBody List<MarkDetail> markDetailList) {
        markDetailList.forEach(mark -> {
            String subject = "Thông báo điểm VPoint VMG Media tháng " + mark.getMonth() + " năm " + mark.getYear();
            String body = "Tổng điểm Vpoint tháng " + mark.getMonth() + " năm " + mark.getYear() + " của bạn là " + "<strong>" + mark.getTotalPoint() + "</strong>"+

                    "<br> <br>Vui lòng truy cập <a href='http://localhost:8081/login'>hệ thống quản lý điểm Vpoint</a> để xem chi tiết.";
            User user = userRepository.findUserByStaffId(mark.getStaffId()).get();
            mailer.queue(user.getEmail(), subject, body);

        });
//            mailInfoList.forEach(mailer::queue);
            return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
