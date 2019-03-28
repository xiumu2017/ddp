package com.paradise.ddp.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author dzhang
 */
@Slf4j
@Component
public class TransitMonitor {
    @Value("${robotToken}")
    private String token;

    @Scheduled(cron = "* 0/1 * * * ?")
    public void monitorAndPush() {
        log.info(">>> 1 m");
    }
}
