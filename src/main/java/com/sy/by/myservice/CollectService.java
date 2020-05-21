package com.sy.by.myservice;

import com.sy.by.bean.Collect;
import com.sy.by.mapper.CollectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CollectService {

    @Autowired
    CollectMapper collectMapper;

    //查找所有
    public ArrayList<Collect> selectAllCollectByUser(int user_id)
    {
        return collectMapper.selectCollectByUser(user_id);
    }
    //增加
    public void insertCollect(Collect collect)
    {
        collectMapper.insertCollect(collect);
    }
    //删除
    public void deleteCollect(Collect collect)
    {
        collectMapper.deleteCollect(collect);
    }
}
