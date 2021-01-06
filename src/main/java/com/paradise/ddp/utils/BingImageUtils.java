
package com.paradise.ddp.utils;

import chatbot.message.MarkdownMessage;
import chatbot.message.Message;
import com.alibaba.fastjson.JSONObject;
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
import java.util.List;

/**
 * @author dzhang
 */
@Slf4j
public class BingImageUtils {

    private static final String BING_URL = "https://cn.bing.com/";
    private static final String BING_IMAGE_URL = "https://cn.bing.com/HPImageArchive.aspx?format=js&pid=hp";

    /**
     * 获取 bing 图片
     *
     * @param index  图片索引 index；0 代表当天，1代表昨天，以此类推
     * @param number 图片数量，最大值为 8，即返回 8张 图片地址
     * @return {@link BingResult}
     * @throws IOException 网络异常
     */
    public static BingResult getBingImage(String index, String number) throws IOException {
        BingResult bingResult = new BingResult();
        HttpClient httpclient = HttpClients.createDefault();
        StringBuilder stringBuilder = new StringBuilder(BING_IMAGE_URL);
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
        }
        return bingResult;
    }

    public static void main(String[] args) {
        try {
            BingResult result = getBingImage("0", "8");
            if (result != null) {
                List<BingImage> images = result.getImages();
                int x = 0;
                for (BingImage image : images) {
                    log.info("序号：{}", x);
                    log.info("name:{}", image.getCopyright());
                    log.info("url:{}", image.getUrl());
                    log.info("{}", image);
                    x++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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

}
