package com.vpoint.vpointtool;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableScheduling
public class VpointToolApplication {

    public static void main(String[] args) {
        SpringApplication.run(VpointToolApplication.class, args);
        String a = "qwer";
        String b = "Qwer";
        System.out.println(a.equals(b));
        System.out.println(a==b);
    }

}
