package com.paradise.ddp.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class TestApiCancelUtils {
    public static void main(String[] args) throws IOException {
        Long startTime = System.currentTimeMillis();
        String apiUrl = "http://61.132.228.55:18081/tomdog/";
        String method = "/api/task/cancel?";
        String appId = "Rmm2Q4yF";
        String username = "api";
        String privateKey = "a8a2ddbf160b1b3beb416837f3936189d6f2c26e";
        // 构造方法参数 - 创建淘宝流量任务
        Map<String, Object> paramMap = new HashMap<>(16);
        paramMap.put("appId", appId);
        paramMap.put("taskId", "15788820816270000123");
        paramMap.put("timestamp", System.currentTimeMillis() / 1000);
        paramMap.put("username", username);
        // 按照字典排序
        TreeMap<String, Object> sortedParams = new TreeMap<>(paramMap);
        // 拼接参数为key=value形式
        StringBuilder sb = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, Object> param : sortedParams.entrySet()) {
            String value = String.valueOf(param.getValue());
            if (StringUtils.isEmpty(value)) {
                continue;
            }
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            sb.append(param.getKey()).append("=").append(value);
        }
        StringBuilder urlBuilder = sb.append("&").append(privateKey);
        // url-encode，md5加密得到签名
        String url = URLEncoder.encode(urlBuilder.toString(), "UTF-8");
        String sign = DigestUtils.md5DigestAsHex(url.getBytes());
        // 调用API
        url = apiUrl + method + sb.toString() + "&sign=" + sign;
        HttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = httpclient.execute(httpGet);
        if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            String result = EntityUtils.toString(httpResponse.getEntity());
            System.out.println(result);
        }
        System.out.println((System.currentTimeMillis() - startTime) / (1000));
    }
}
