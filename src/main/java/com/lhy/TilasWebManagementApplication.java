package com.lhy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.server.servlet.context.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class TilasWebManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TilasWebManagementApplication.class, args);
    }

}
