package com.sy.by.myservice;

import com.sy.by.bean.Test_paper;
import com.sy.by.mapper.Test_paperMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class Test_paperService {
    @Autowired
    Test_paperMapper test_paperMapper;

    //增加
    public void insertTest_paper(Test_paper test_paper)
    {
         test_paperMapper.insertTest_paper(test_paper);
    }
    //更新
    public void updateTest_paper(Test_paper test_paper)
    {
        test_paperMapper.updateTest_paper(test_paper);
    }
    //查看单个
    public Test_paper selectByTest_id(int test_id)
    {
        return test_paperMapper.selectByTest_id(test_id);
    }
    //查询某个用户所有的考试记录
    public ArrayList<Test_paper> selectAllByUser_id(int user_id)
    {
        return test_paperMapper.selectAllByuser_Id(user_id);
    }

}
