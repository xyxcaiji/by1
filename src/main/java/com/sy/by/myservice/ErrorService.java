package com.sy.by.myservice;

import com.sy.by.bean.Errorr;
import com.sy.by.mapper.ErrorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ErrorService {
    @Autowired
    ErrorMapper errorMapper;

    //增加错误
    public void insertError(Errorr error)
    {
        errorMapper.insertError(error);
    }
    //查找错误
    public ArrayList<Errorr> selectAllError(String subject_name,int user_id)
    {
       return  errorMapper.selectBySubjectName(subject_name,user_id);
    }

    //查询全部错误
    public ArrayList<Errorr> selectAll(int user_id)
    {
        return errorMapper.selectAll(user_id);
    }

    //查询单个错误
    public Errorr selectByTopicId(int user_id,int topic_id)
    {
        return errorMapper.selectByTopicId(user_id, topic_id);
    }
}
