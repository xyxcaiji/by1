package com.sy.by.myservice;
/**
 * Question_bank
 * 具体的业务逻辑处理层
 */

import com.sy.by.bean.Question_bank;
import com.sy.by.mapper.Question_bankMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Question_bankService {

    @Autowired
    Question_bankMapper questionBankMapper;
    //增加
    public void insert(Question_bank question_bank)
    {
        questionBankMapper.insertQuestion_bank(question_bank);
    }
    //删除
    public void delete(int id)
    {
        questionBankMapper.delete(id);
    }
    //查找全部
    public ArrayList<Question_bank> selectAll()
    {
        return questionBankMapper.selectAllquestion();
    }
    //更新
    public void updateQuestionBank(Question_bank question_bank)
    {
        questionBankMapper.update(question_bank);
    }
    //查找单个
    public Question_bank selectByid(int id){ return questionBankMapper.selectByid(id);}
    //查找所有的课程id和课程名字
    public ArrayList<Question_bank> selectAllIdAndSubject(){ return questionBankMapper.selectAllIdAndSubject(); }

}
