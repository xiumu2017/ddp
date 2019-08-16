
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
    public void print() {
        log.info("server is running ...");
    }

    /**
     * 每天9点 推送壁纸 到钉钉群
     */
    @Scheduled(cron = "0 0 9 * * ?")
    public void scheduled1() {
        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        DentalCabotClient client = new DentalCabotClient();
        try {
            BingResult bingResult = BingImageUtils.getBingImage("0", "1");
            client.send("ae6476d73f64ddd5e96daf17d9acbedf7c0ea24e8eef1e4e7d468b564618d58c",
                    bingResult2Msg(bingResult.getImages().get(0)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return;
        }
        log.info("<<< Bing push success!");
    }

    public static void main(String[] args) {
        DentalCabotClient client = new DentalCabotClient();
        try {
            String token = "ae6476d73f64ddd5e96daf17d9acbedf7c0ea24e8eef1e4e7d468b564618d58c";
            PoemEntity poemEntity = PoemSendUtils.getPoem();
            client.send(token, Poem2Md.poem2Md(poemEntity));
            BingResult bingResult = BingImageUtils.getBingImage(String.valueOf(0), "1");
            client.send(token, bingResult2Msg(bingResult.getImages().get(0)));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
