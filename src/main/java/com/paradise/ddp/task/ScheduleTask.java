
package com.paradise.ddp.task;

import com.dingtalk.chatbot.DentalCabotClient;
import com.paradise.ddp.constant.CommonUrl;
import com.paradise.ddp.entity.BingImage;
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

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
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
    @Value("${secret}")
    public String secret;

    @Scheduled(cron = "0 0 12 * * ?")
    public void scheduled() {
        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        DentalCabotClient client = new DentalCabotClient();
        try {
            PoemEntity poemEntity = PoemSendUtils.getPoem();
            client.send(token, Poem2Md.poem2Md(poemEntity), secret);
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
            BingImage image = bingResult.getImages().get(0);
            client.send(token, bingResult2Msg(image), secret);
            String title = image.getUrl().substring(image.getUrl().indexOf("=") + 1, image.getUrl().indexOf("&"));
            download(CommonUrl.BING_URL + image.getUrl(), "E:\\bing\\" + title);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return;
        }
        log.info("<<< Bing push success!");
    }

    private static void download(String url, String path) {
        URL url1;
        try {
            url1 = new URL(url);
            URLConnection uc = url1.openConnection();
            InputStream inputStream = uc.getInputStream();
            FileOutputStream out = new FileOutputStream(path);
            int j;
            while ((j = inputStream.read()) != -1) {
                out.write(j);
            }
            inputStream.close();
        } catch (IOException e) {
            log.error(e.getLocalizedMessage(), e);
        }

    }

    public static void main(String[] args) {
        DentalCabotClient client = new DentalCabotClient();
        try {
            String token = "ba181262d3c9fec090a82b8835bdf5d277d78c930a3c878dc949e40633d39eee";
//            PoemEntity poemEntity = PoemSendUtils.getPoem();
//            client.send(token, Poem2Md.poem2Md(poemEntity),
//                    "SEC562a6359d5b1943bc9393f76ed4bcb25d2503eedd8d6c1df0490935eeb33873f");
            BingResult bingResult = BingImageUtils.getBingImage(String.valueOf(0), "1");
//            client.send(token, bingResult2Msg(bingResult.getImages().get(0)),
//                    "SEC562a6359d5b1943bc9393f76ed4bcb25d2503eedd8d6c1df0490935eeb33873f");
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
