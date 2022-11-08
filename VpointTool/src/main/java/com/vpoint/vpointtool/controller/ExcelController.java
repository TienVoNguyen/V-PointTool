package com.vpoint.vpointtool.controller;

import com.vpoint.vpointtool.helper.ExcelHelper;
import com.vpoint.vpointtool.models.login.User;
import com.vpoint.vpointtool.services.IExcelService;
import com.vpoint.vpointtool.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/excel")
public class ExcelController {

    @Autowired
    private IExcelService excelService;

    @GetMapping("/export")
    public ResponseEntity<Resource> getFile() {
        String filename = "users.xlsx";
        InputStreamResource file = new InputStreamResource(excelService.exportUser());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/vnd.ms-excel"))
                .body(file);
    }
}
