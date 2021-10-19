package sw.java.elk.ossdemo;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.tomcat.util.security.MD5Encoder;
import sw.java.elk.util.RSAUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Arrays;
import java.util.Base64;

public class Demo {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    static String endpoint = "oss-cn-shenzhen.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    static String accessKeyId;
    public final static String pri_k = "MIIBVQIBADANBgkqhkiG9w0BAQEFAASCAT8wggE7AgEAAkEAlJGfVHaVdvV6sDBa1klGQNnZEfeMJOaYUBuJTWqGaVfr+w8Cqlo3ytt+iwQUeEX8O5/ZDwp3Vx25yO7i8gRRcwIDAQABAkArthScsy8SivMH95IiTfi/h9jX8m9nQ/k4SIHDJz5Yq7K4BUgVQJIpywrCxpWhaOU7FEF3wHxx2kWcmQ27z/ThAiEA7JfnllbETRhfTmTDZQ/Bufjaerao49Q2cDwPMNdnNZkCIQCgwVaiK2ATFHNdc6M4DSqbRIfB+RUFQtIodBBxb23O6wIhAMCEmx3qXLGfM25G7/g8ahc6OA3CtIsvE10ggfvsSz+BAiBfEbgQdCJ/ZirL66Vtcqvt41t+JbZ+xAPRlMJcs493PQIhAKdoCx0uHmlGnmPGovXCDLmOhCGcYqcgmMbsrslDFVkP";

    static String accessKeySecret;

    static {
        try {
            accessKeySecret = RSAUtils.decrypt("av4C5ajgTSTX1sQDKakODdzEPjxnWig4jZBiSkKt8/c64YsKf6EGr45rxAf9dpwfKYs3pHQs7UXOVWbXquRAxA==", RSAUtils.getPrivateKey(pri_k));
            accessKeyId = RSAUtils.decrypt("c7W2cdVOPx9piLCA9O+RFDsARWQZnknVyymlk/xDZaeeDeQfNc80P29wF3SsFaQ0kSjhBP3pf2ht/CgXXrhFYw==", RSAUtils.getPrivateKey(pri_k));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static String bucketName = "shaun-oss";


    final static String pub_k = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJSRn1R2lXb1erAwWtZJRkDZ2RH3jCTmmFAbiU1qhmlX6/sPAqpaN8rbfosEFHhF/Duf2Q8Kd1cducju4vIEUXMCAwEAAQ==";


    public static void main(String[] args) throws Exception {

        //upload();
        String encrypt = RSAUtils.encrypt(accessKeyId, RSAUtils.getPublicKey(pub_k));
        System.out.println(encrypt);
        String decryptData = RSAUtils.decrypt("c7W2cdVOPx9piLCA9O+RFDsARWQZnknVyymlk/xDZaeeDeQfNc80P29wF3SsFaQ0kSjhBP3pf2ht/CgXXrhFYw==", RSAUtils.getPrivateKey(pri_k));

        System.out.println("解密后内容:" + decryptData);


    }

    public static void upload() {

// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
// 创建PutObjectRequest对象。
        String content = "Hello OSS";
// <yourObjectName>表示上传文件到OSS时需要指定包含文件后缀在内的完整路径，例如abc/efg/123.jpg。
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, "rabbit2.jpg", new File("/Users/shaun/Downloads/rabbit.jpg"));
// 如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
// ObjectMetadata metadata = new ObjectMetadata();
// metadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS, StorageClass.Standard.toString());
// metadata.setObjectAcl(CannedAccessControlList.Private);
// putObjectRequest.setMetadata(metadata);

// 上传字符串。
        PutObjectResult putObjectResult = ossClient.putObject(putObjectRequest);
        System.out.println(putObjectResult.getETag());
// 关闭OSSClient。
        ossClient.shutdown();
    }


    public static void downLoad() {
        String objectName = "rabbit.jpg";
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
// 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File("aabbcc.jpg"));
// 关闭OSSClient。
        ossClient.shutdown();
    }

    public static void ifExit() {
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 判断文件是否存在。doesObjectExist还有一个参数isOnlyInOSS，如果为true则忽略302重定向或镜像；如果
        //为false，则考虑302重定向或镜像。
        boolean found = ossClient.doesObjectExist(bucketName, "rabbit.jpg");
        System.out.println(found);

// 关闭OSSClient。
        ossClient.shutdown();
    }
}
