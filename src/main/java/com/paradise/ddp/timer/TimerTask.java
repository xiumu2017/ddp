package com.paradise.ddp.timer;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * @author dzhang
 */
@Service
public class TimerTask {

    private static long initialDelay = 1000L;
    private static long period = 1000L;

    public TimerTask() {
        ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        executorService.scheduleAtFixedRate(() -> {
            //do something
            System.out.println("<<<");
        }, initialDelay, period, TimeUnit.MILLISECONDS);
    }
}
