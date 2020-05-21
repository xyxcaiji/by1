package com.sy.by.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by lightClouds917
 * Date 2018/2/7
 * Description:aliyunOSSUtil
 */
@Component
public class AliyunOSSUtil {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(AliyunOSSUtil.class);
 //   private static  AliyunOssProperties aliyunOssProperties;
//    endpoint: http://oss-cn-beijing.aliyuncs.com
//    bucketName: niu-test1
//    accessKeyId: LTAI4FucJBZx3ztzCDTcARWs
//    accessKeySecret: UI1FkQNm4fqzwL4QybhGWn3hsdio3H
//    dir: image
    @Value("${ali-oss.endpoint}")
    private  String endpoint;
    @Value("${ali-oss.access-key-id}")
    private    String accessKeyId;
    @Value("${ali-oss.access-key-secret}")
    private    String accessKeySecret;
    @Value("${ali-oss.bucket-name}")
    private    String bucketName;
    @Value("${ali-oss.dir}")
    private    String dir;

    public  String upload(File file){


        logger.info("=========>OSS文件上传开始："+file.getName());

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        if(null == file){
            return null;
        }

        OSSClient ossClient = new OSSClient(endpoint,accessKeyId,accessKeySecret);
        try {
            //容器不存在，就创建
            if(! ossClient.doesBucketExist(bucketName)){
                ossClient.createBucket(bucketName);
                //将其设公共读写
                CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
                //设置权限 这里是公开读
                createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
                ossClient.createBucket(createBucketRequest);
            }
            //创建文件路径
            String fileUrl = dir+"/"+(dateStr + "/" + UUID.randomUUID().toString().replace("-","")+"-"+file.getName());
            //上传文件
            PutObjectResult result = ossClient.putObject(new PutObjectRequest(bucketName, fileUrl, file));
            //设置bucketName权限 这里是公开读
            ossClient.setBucketAcl(bucketName,CannedAccessControlList.PublicRead);

            //返回图片所在的全部路径，方便前端访问
            //设置过期时间为100年
            Date expiration = new Date(new Date().getTime() + 3600l * 1000 * 24 * 365 * 10);
            // 生成URL
            OSSClient ossClient1 = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            URL url = ossClient.generatePresignedUrl(bucketName, fileUrl, expiration);
            if(null != url){
                logger.info("==========>OSS文件上传成功,OSS地址："+url.toString());
                return url.toString();
            }
        }catch (OSSException oe){
            logger.error(oe.getMessage());
        }catch (ClientException ce){
            logger.error(ce.getMessage());
        }finally {
            //关闭
            ossClient.shutdown();
        }
        return null;
    }


    public String uploadBlog(MultipartFile file){
        //判断格式是否正确
        String[] IMAGE_TYPE = new String[]{".bmp", ".jpg", ".jpeg", ".gif", ".png"};
        boolean isLegal = false;
        for (String type : IMAGE_TYPE) {
            if (StringUtils.endsWithIgnoreCase(file.getOriginalFilename(),
                    type)) {
                isLegal = true;
                break;
            }
        }
        //如果不符合预定义的格式
        if (isLegal==false)
        {
            logger.error("上传文件格式失败");
            //格式不对
            return "0";
        }
        logger.info("============>文件上传");
        try {

            if(null != file){
                String filename = file.getOriginalFilename();
                //  System.out.println("filename"+filename);
                if(!"".equals(filename.trim())){
                    File newFile = new File(filename);
                    FileOutputStream os = new FileOutputStream(newFile);
                    os.write(file.getBytes());
                    os.close();
                                        file.transferTo(newFile);
                    //上传到OSS
                    String finalPath = this.upload(newFile);
                    logger.info("路径=======>"+finalPath);
                    String path = System.getProperty("user.dir");
                    String filepath=path+File.separator+file.getOriginalFilename();
                    File file1=new File(filepath);
                    if(file1.exists())
                    {
                        file1.delete();
                    }else {
                        logger.info("============>删除遗留在项目中的文件失败");
                    }
                    if(finalPath!=null)
                    {
                        return finalPath;
                    }
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }
}