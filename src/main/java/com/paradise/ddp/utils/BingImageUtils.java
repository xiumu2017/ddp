
package com.paradise.ddp.utils;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.chatbot.message.MarkdownMessage;
import com.dingtalk.chatbot.message.Message;
import com.paradise.ddp.constant.CommonUrl;
import com.paradise.ddp.entity.BingImage;
import com.paradise.ddp.entity.BingResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Random;

/**
 * bing 壁纸工具类
 *
 * @author dzhang
 */
@Slf4j
public class BingImageUtils {

    public static BingResult getBingImage(String index, String number) throws IOException {
        BingResult bingResult = new BingResult();
        HttpClient httpclient = HttpClients.createDefault();
        StringBuilder stringBuilder = new StringBuilder(CommonUrl.BING_IMAGE_URL);
        if (StringUtils.isNotEmpty(index)) {
            stringBuilder.append("&idx=");
            stringBuilder.append(index);
        }
        if (StringUtils.isNotEmpty(number)) {
            stringBuilder.append("&n=");
            stringBuilder.append(number);
        }
        stringBuilder.append("&nc");
        stringBuilder.append(System.currentTimeMillis());

        HttpGet httpGet = new HttpGet(stringBuilder.toString());
        HttpResponse httpResponse = httpclient.execute(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(httpResponse.getEntity());
            JSONObject obj = JSONObject.parseObject(result);
            bingResult = obj.toJavaObject(BingResult.class);
            log.info("获取到的壁纸路径：{}", bingResult.getImages().get(0).getUrl());
        }
        return bingResult;
    }

    /**
     * bing 壁纸转 钉钉消息
     *
     * @param image bing 壁纸
     * @return 钉钉消息
     */
    public static Message bingResult2Msg(BingImage image) {
        MarkdownMessage message = new MarkdownMessage();
        if (StringUtils.isNotEmpty(image.getCopyright())) {
            message.setTitle(image.getCopyright());
        } else {
            message.setTitle("每日BING壁纸");
        }
        message.add(MarkdownMessage.getHeaderText(1, image.getCopyright()));
        message.add(MarkdownMessage.getImageText(CommonUrl.BING_URL + image.getUrl()));
        message.add(MarkdownMessage.getLinkText(image.getCopyright(), CommonUrl.BING_URL + image.getCopyrightlink()));
        return message;
    }

    public static String getRandom(int i) {
        int ran = new Random().nextInt(i);
        log.info("当前随机数字为：" + ran);
        return String.valueOf(ran);
    }
}
