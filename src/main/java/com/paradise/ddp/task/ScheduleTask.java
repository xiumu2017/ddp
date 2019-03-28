
package com.paradise.ddp.task;

import com.dingtalk.chatbot.DentalCabotClient;
import com.paradise.ddp.entity.BingResult;
import com.paradise.ddp.entity.PoemEntity;
import com.paradise.ddp.utils.BingImageUtils;
import com.paradise.ddp.utils.Poem2Md;
import com.paradise.ddp.utils.PoemSendUtils;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Calendar;

import static com.paradise.ddp.utils.BingImageUtils.bingResult2Msg;
import static com.paradise.ddp.utils.BingImageUtils.getRandom;

/**
 * @author dzhang
 */
@Slf4j
@Component
public class ScheduleTask {

    @Value("${bingImgToken}")
    private String token;

    @Scheduled(cron = "0 0 12 * * ?")
    public void scheduled() {
        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        DentalCabotClient client = new DentalCabotClient();
        try {
            PoemEntity poemEntity = PoemSendUtils.getPoem();
            client.send(token, Poem2Md.poem2Md(poemEntity));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    @Scheduled(cron = "0 */1 * * * ?")
    public void print(){
        log.info("server is running ...");
    }


    @Scheduled(fixedRate = 600000)
    public void scheduled1() {
        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        DentalCabotClient client = new DentalCabotClient();
        try {
            BingResult bingResult = BingImageUtils.getBingImage(getRandom(10), "1");
            client.send(token, bingResult2Msg(bingResult.getImages().get(0)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return;
        }
        log.info("<<< Bing push success!");
    }
}
