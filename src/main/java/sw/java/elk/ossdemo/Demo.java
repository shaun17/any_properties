package sw.java.elk.ossdemo;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;

import java.io.ByteArrayInputStream;
import java.io.File;

public class Demo {
    // Endpoint以杭州为例，其它Region请按实际情况填写。
    static String endpoint = "oss-cn-shenzhen.aliyuncs.com";
    // 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    static String accessKeyId = "LTAI4FsjUYeCudKXpR yc7oZu";
    static String accessKeySecret = "rGpD9DEuK2m27jFGRnGCcXLq852w8l";
    static String bucketName = "shaun-oss";


    public static void main(String[] args) {

        upload();


    }

    public static void upload(){

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


    public static void downLoad(){
        String objectName = "rabbit.jpg";
// 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
// 下载OSS文件到本地文件。如果指定的本地文件存在会覆盖，不存在则新建。
        ossClient.getObject(new GetObjectRequest(bucketName, objectName), new File("aabbcc.jpg"));
// 关闭OSSClient。
        ossClient.shutdown();
    }

    public static void ifExit(){
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

// 判断文件是否存在。doesObjectExist还有一个参数isOnlyInOSS，如果为true则忽略302重定向或镜像；如果
        //为false，则考虑302重定向或镜像。
        boolean found = ossClient.doesObjectExist(bucketName, "rabbit.jpg");
        System.out.println(found);

// 关闭OSSClient。
        ossClient.shutdown();
    }
}
