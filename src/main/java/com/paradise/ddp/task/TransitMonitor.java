package com.paradise.ddp.task;

import com.dingtalk.chatbot.DentalCabotClient;
import com.dingtalk.chatbot.message.TextMessage;
import com.paradise.transit.oracle.OracleInfo;
import com.paradise.transit.oracle.QueryForTransit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.ParseException;

/**
 * @author dzhang
 */
@Slf4j
@Component
public class TransitMonitor {

    /**
     * 报警时间差值，默认10m 600000毫秒
     */
    private static final Long WARNING_LEVEL = 300000L;

    private DentalCabotClient client = new DentalCabotClient();

    @Value("${transitMonitorToken}")
    private String token;

    @Scheduled(cron = "0 */10 * * * ?")
    public void monitorAndPush() throws ParseException {
        OracleInfo info = new OracleInfo("jdbc:oracle:thin:@111.38.109.176:1521:orcl", "jhsgjptqt", "jhsgjptqt");
        Long lastTime = QueryForTransit.queryLastPushTime(info);
        if (lastTime != null) {
            if (lastTime > WARNING_LEVEL) {
                // 记录到推送表

                // or 直接推送
                log.error("Data-Sync-Server for Chi-Zhou City is overtime!");
                client.send(token, new TextMessage("please check Data-Sync-Server for Chi-Zhou City"));
            } else {
                // 记录巡检结果
                log.info("Data-Sync-Server for Chi-Zhou City is running normally...");
                client.send(token, new TextMessage("Data-Sync-Server for Chi-Zhou City is running normally..."));
            }
        } else {
            client.send(token, new TextMessage("query oracle database fail, no response!"));
            log.error("query fail, no response!");
        }

    }


}
