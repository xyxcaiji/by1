package com.sy.by.mycontroller;

import com.sy.by.util.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PicController {

    @Autowired
    AliyunOSSUtil aliyunOSSUtil;

    @ResponseBody
    @PostMapping("/uploadPic")
    public String uploadPic(@RequestParam(value = "file",required =false) MultipartFile file)
    {
        //向服务器上传图片
        String uploadBlog = aliyunOSSUtil.uploadBlog(file);
        if(uploadBlog.equals("0"))
        {
            //文件格式错误
            return "0";
        }
        return uploadBlog;
    }

}
