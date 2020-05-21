package com.sy.by.myservice;

import com.sy.by.bean.Wen_answer;
import com.sy.by.bean.Wen_juan;
import com.sy.by.mapper.WenJuanMapper;
import org.apache.coyote.OutputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WenService {
    @Autowired
    WenJuanMapper wenJuanMapper;

//    //增加问卷题目
//    public void insertWenJuan(Wen_juan wen_juan)
//    {
//        wenJuanMapper.insertJuan(wen_juan);
//    }
//    //查找所有问卷题目
//    public List<Wen_juan> selectAllWen()
//    {
//        return wenJuanMapper.selectAllWen();
//    }
    //增加问卷答案
    public void insertWenAnswer(Wen_answer wen_answer)
    {
        wenJuanMapper.insert(wen_answer);
    }
    //查询所有答案
    public List<Wen_answer> selectAllAnswer()
    {
        return wenJuanMapper.selectAllWenAnswer();
    }
    //查询所有的用户
    public List<String> selectAllUser_name(){
        return wenJuanMapper.selectAllUser_name();
    }
}
