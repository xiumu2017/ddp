package com.dingtalk.chatbot;

import com.alibaba.fastjson.JSONObject;
import com.dingtalk.chatbot.message.Message;
import com.paradise.ddp.utils.SendResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author dzhang
 */
@Slf4j
public class DentalCabotClient {

    private HttpClient httpclient = HttpClients.createDefault();

    private static String webHookPrefix = "https://oapi.dingtalk.com/robot/send?access_token=";

    private String createSign(String secret) {
        long timestamp = System.currentTimeMillis();
        String stringToSign = timestamp + "\n" + secret;
        Mac mac;
        try {
            mac = Mac.getInstance("HmacSHA256");
            byte[] signData;
            mac.init(new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), "HmacSHA256"));
            signData = mac.doFinal(stringToSign.getBytes(StandardCharsets.UTF_8));
            return URLEncoder.encode(new String(Base64.encodeBase64(signData)), StandardCharsets.UTF_8.displayName());
        } catch (UnsupportedEncodingException | InvalidKeyException | NoSuchAlgorithmException e) {
            log.error(e.getLocalizedMessage(), e);
            return null;
        }
    }

    private String createUrl(String secret, String token) {
        return webHookPrefix + token + "&timestamp=" + System.currentTimeMillis() + "&sign=" + createSign(secret);
    }

    private SendResult push(Message message, String url) {
        HttpPost httppost = new HttpPost(url);
        httppost.addHeader("Content-Type", "application/json; charset=utf-8");
        StringEntity se = new StringEntity(message.toJsonString(), "utf-8");
        httppost.setEntity(se);

        SendResult sendResult = new SendResult();
        try {
            HttpResponse response = httpclient.execute(httppost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String result = EntityUtils.toString(response.getEntity());
                JSONObject obj = JSONObject.parseObject(result);

                Integer errCode = obj.getInteger("errcode");
                sendResult.setErrorCode(errCode);
                sendResult.setErrorMsg(obj.getString("errmsg"));
                sendResult.setIsSuccess(errCode.equals(0));
            }
        } catch (IOException e) {
            log.error("dingding msg push error: ");
            log.error(e.getLocalizedMessage(), e);
        }
        log.info(sendResult.toString());
        return sendResult;
    }

    public void send(String tokens, Message message, String secret) {
        for (String token : parseToken(tokens)) {
            this.push(message, createUrl(secret, token));
        }
    }

    private String[] parseToken(String tokens) {
        if (StringUtils.isNotEmpty(tokens)) {
            return tokens.split("\\|");
        }
        return new String[]{};
    }

}


