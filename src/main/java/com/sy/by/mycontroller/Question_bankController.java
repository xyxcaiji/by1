package com.sy.by.mycontroller;

import com.sy.by.bean.Question_bank;
import com.sy.by.myservice.Question_bankService;
import com.sy.by.util.AliyunOSSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;

@Controller
public class Question_bankController {

    @Autowired
    Question_bankService question_bankService;

    //添加项目
    @ResponseBody
    @GetMapping("/insertSubjects")
    public String insertQuestion_bank(Question_bank question_bank)
    {
        boolean b=false;
        ArrayList<Question_bank> question_banks = question_bankService.selectAll();
        for(Question_bank questionBank11:question_banks)
        {
            if(questionBank11.getSubject().equals(question_bank.getSubject()))
            {
                b=true;
            }
        }
        //如果不存在，添加成功
        if(b==false)
        {
            question_bankService.insert(question_bank);
            return "1";
        }
        //否则 失败
        return "0";
    }

    @ResponseBody
    @GetMapping("/deleteSubjects")
    public String deleteByid(int id)
    {
        try {
            String subject = question_bankService.selectByid(id).getSubject();
            question_bankService.delete(id);
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }

    //更新 科目表
    @ResponseBody
    @GetMapping("/editSubjects")
    public String updateSubject(Question_bank question_bank/*,@RequestParam(value = "upload",required =false) MultipartFile file*/)
    {
        try {
//            String subject = question_bankService.selectByid(question_bank.getId()).getSubject();
//            //向服务器上传图片
//            String uploadBlog = aliyunOSSUtil.uploadBlog(file);
//            if(uploadBlog.equals("0"))
//            {
//                //文件格式错误
//                return "-1";
//            }
//            //得到上传图片的路径
//            question_bank.setSubject_pic(uploadBlog);
            question_bankService.updateQuestionBank(question_bank);
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }
    @ResponseBody
    @GetMapping("/subjects")
    public ArrayList<Question_bank> selectAllSubject()
    {
        ArrayList<Question_bank> question_banks = question_bankService.selectAll();
        return question_banks;
    }

}
