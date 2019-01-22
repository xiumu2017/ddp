package com.paradise.ddp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DdpApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdpApplication.class, args);
    }
}
