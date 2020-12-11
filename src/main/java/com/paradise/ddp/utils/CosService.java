package com.paradise.ddp.utils;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * 对象存储service
 * <a href="https://github.com/tencentyun/cos-java-sdk-v5/tree/master/src/main/java/com/qcloud/cos/demo">
 * 官方demo</a>
 *
 * @author Paradise
 */
@Slf4j
@Service
public class CosService {

    private COSClient cosClient;

    @PostConstruct
    public void initCosClient() {
        // 1 初始化用户身份信息（secretId, secretKey）。
        String secretId = "AKIDuc4Ufdqq57tPpuoMnyveReHhuvhGbfnv";
        String secretKey = "40kEVWMjHdpoY5QjOBkz0mLflfdMp44q";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的区域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-shanghai");
        ClientConfig clientConfig = new ClientConfig(region);
        // 3 生成 cos 客户端。
        cosClient = new COSClient(cred, clientConfig);
    }

    /**
     * 创建存储桶
     *
     * @param bucket 存储桶名称，格式：BucketName-APPID "examplebucket-1250000000"
     */
    public void createBuck(String bucket, CannedAccessControlList permission) {
        CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);
        // 设置 bucket 的权限为 Private(私有读写), 其他可选有公有读私有写, 公有读写
        if (permission != null) {
            createBucketRequest.setCannedAcl(permission);
        } else {
            createBucketRequest.setCannedAcl(CannedAccessControlList.Private);
        }
        try {
            Bucket bucketResult = cosClient.createBucket(createBucketRequest);
            log.info("创建存储桶 Result:{}", bucketResult);
        } catch (CosClientException serverException) {
            log.error("创建存储桶 出现异常：{}", serverException.getLocalizedMessage(), serverException);
        }
    }

    public List<Bucket> listBucket() {
        return cosClient.listBuckets();
    }

    public void upload(File file, String bucketName) {
        if (file == null) {
            return;
        }
        if (!StringUtils.hasLength(bucketName)) {
            throw new IllegalArgumentException("bucketName can not be empty");
        }
        // 指定要上传到的存储桶
//        String bucketName = "bing-wallpaper-1256237186";
        // 指定要上传到 COS 上对象键
        String key = file.getName();
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        log.info("文件上传 result：{}", putObjectResult);
    }

    public void upload(InputStream inputStream, String bucketName, String key) {
        if (inputStream == null) {
            return;
        }
        if (!StringUtils.hasLength(bucketName)) {
            throw new IllegalArgumentException("bucketName can not be empty");
        }
        // 指定要上传到的存储桶
//        String bucketName = "bing-wallpaper-1256237186";
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, null);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        log.info("文件上传 result：{}", putObjectResult);
    }

}
