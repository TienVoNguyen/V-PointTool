package com.vpoint.vpointtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class VpointToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(VpointToolApplication.class, args);
    }

}
