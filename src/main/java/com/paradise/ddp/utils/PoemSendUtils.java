
package com.paradise.ddp.utils;


import com.alibaba.fastjson.JSONObject;
import com.dingtalk.chatbot.DentalCabotClient;
import com.dingtalk.chatbot.demo.TestConfig;
import com.paradise.ddp.entity.PoemEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Calendar;

/**
 * @author dzhang
 */
public class PoemSendUtils {
    private static final Logger logger = LoggerFactory.getLogger(PoemSendUtils.class);

    public static PoemEntity getPoem() throws IOException {
        String URL = "https://v2.jinrishici.com/one.json";
        String TOKEN = "Xtw9qKYqtWSYAz51waUbbalXV/w2+w87";

        PoemEntity poemEntity = new PoemEntity();
        HttpClient httpclient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(URL);
        httpGet.addHeader("Content-Type", "application/json; charset=utf-8");
        httpGet.addHeader("X-User-Token", TOKEN);

        HttpResponse httpResponse = httpclient.execute(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(httpResponse.getEntity());
            JSONObject obj = JSONObject.parseObject(result);
            poemEntity = obj.toJavaObject(PoemEntity.class);
            logger.info(obj.toJSONString());
            logger.info(">>>>>>>>>>>>>>>");
        }
        return poemEntity;
    }

    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        int h = calendar.get(Calendar.HOUR_OF_DAY);
        System.out.print(h);
//        DentalCabotClient client = new DentalCabotClient();
//        try {
//            PoemEntity poemEntity = PoemSendUtils.getPoem();
//            client.send(TestConfig.CHATBOT_WEBHOOK, Poem2Md.poem2Md(poemEntity));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

}
