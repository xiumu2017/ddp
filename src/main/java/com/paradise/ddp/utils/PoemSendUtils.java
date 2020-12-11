
package com.paradise.ddp.utils;


import com.alibaba.fastjson.JSONObject;
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

/**
 * @author dzhang
 */
public class PoemSendUtils {
    private static final Logger logger = LoggerFactory.getLogger(PoemSendUtils.class);

    public static PoemEntity getPoem() throws IOException {
        String url = "https://v2.jinrishici.com/one.json";
        String token = "Xtw9qKYqtWSYAz51waUbbalXV/w2+w87";

        PoemEntity poemEntity = new PoemEntity();
        HttpClient httpclient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);
        httpGet.addHeader("Content-Type", "application/json; charset=utf-8");
        httpGet.addHeader("X-User-Token", token);

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

}
