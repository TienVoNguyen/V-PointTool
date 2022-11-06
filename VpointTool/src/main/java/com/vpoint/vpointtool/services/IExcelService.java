package com.vpoint.vpointtool.services;

import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public interface IExcelService {

    ByteArrayInputStream exportUser();
}
