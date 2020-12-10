package com.paradise.ddp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Spring-boot 启动类
 *
 * @author dzhang
 */
@EnableScheduling
@SpringBootApplication
public class DdpApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DdpApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DdpApplication.class);
    }
}
