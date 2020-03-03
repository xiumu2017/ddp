
package com.paradise.ddp.task;

import com.dingtalk.chatbot.DentalCabotClient;
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

    private static COSClient cos() {
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = "AKIDuc4Ufdqq57tPpuoMnyveReHhuvhGbfnv";
        String secretKey = "40kEVWMjHdpoY5QjOBkz0mLflfdMp44q";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-shanghai");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        return new COSClient(cred, clientConfig);
    }

    private static void createBuck(COSClient cosClient) {
        //存储桶名称，格式：BucketName-APPID
        String bucket = "examplebucket-1250000000";
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);
        // 设置 bucket 的权限为 Private(私有读写), 其他可选有公有读私有写, 公有读写
        createBucketRequest.setCannedAcl(CannedAccessControlList.Private);
        try {
            Bucket bucketResult = cosClient.createBucket(createBucketRequest);
            System.out.println(bucketResult.toString());
        } catch (CosClientException serverException) {
            serverException.printStackTrace();
        }
    }

    private static void listBucket(COSClient cosClient) {
        List<Bucket> buckets = cosClient.listBuckets();
        for (Bucket bucketElement : buckets) {
            String bucketName = bucketElement.getName();
            String bucketLocation = bucketElement.getLocation();
            System.out.println(bucketName);
            System.out.println(bucketLocation);
        }
    }

    private static void upload(COSClient cosClient) {
        String localFilePath = "E:\\Photo\\IMG_0038.JPG";
        // 指定要上传的文件
        File localFile = new File(localFilePath);
        // 指定要上传到的存储桶
        String bucketName = "bing-wallpaper-1256237186";
        // 指定要上传到 COS 上对象键
        String key = localFile.getName();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);

        System.out.println(putObjectResult.toString());
    }

    public static void main(String[] args) {
        COSClient cosClient = cos();
//        listBucket(cosClient);
        upload(cosClient);
        cosClient.shutdown();
    }
}
