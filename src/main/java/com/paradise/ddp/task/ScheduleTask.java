
package com.paradise.ddp.task;

import chatbot.DentalCabotClient;
import com.paradise.ddp.constant.CommonUrl;
import com.paradise.ddp.entity.BingImage;
import com.paradise.ddp.entity.BingResult;
import com.paradise.ddp.entity.PoemEntity;
import com.paradise.ddp.utils.BingImageUtils;
import com.paradise.ddp.utils.Poem2Md;
import com.paradise.ddp.utils.PoemSendUtils;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;
import java.util.List;

import static com.paradise.ddp.utils.BingImageUtils.bingResult2Msg;

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

    /**
     * 每天中午12点 推送古诗词一首
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void scheduled() {
        try {
            PoemEntity poemEntity = PoemSendUtils.getPoem();
            DentalCabotClient.send(token, Poem2Md.poem2Md(poemEntity), secret);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }

    /**
     * 每天9点 推送壁纸 到钉钉群
     */
    @Scheduled(cron = "0 0 9 * * ?")
    public void scheduled1() {
        try {
            BingResult bingResult = BingImageUtils.getBingImage("0", "1");
            BingImage image = bingResult.getImages().get(0);
            DentalCabotClient.send(token, bingResult2Msg(image), secret);
            String title = image.getUrl().substring(image.getUrl().indexOf("=") + 1, image.getUrl().indexOf("&"));
            download(CommonUrl.BING_URL + image.getUrl(), "./bing/" + title);
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

}
