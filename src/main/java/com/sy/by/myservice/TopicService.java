package com.sy.by.myservice;

import com.sy.by.bean.CollectAndTopic;
import com.sy.by.bean.ErrorTopic;
import com.sy.by.bean.Topic;
import com.sy.by.mapper.TopicMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class TopicService {
    @Autowired
    TopicMapper topicMapper;

    //添加
    public void insertTopic(Topic topic)
    {
        topicMapper.insertTopic(topic);
    }

    //更新
    public void updateTopic(Topic topic)
    {
        topicMapper.updateTopic(topic);
    }

    //通过章节id查找对应的题
    public ArrayList<Topic> selectBySectionId(int section_id)
    {
      return   topicMapper.selectBySectionId(section_id);
    }

    //随机查找题目 随机通过难易程度
    public ArrayList<Topic> selectByRandom(String difficulty,int num,int question_id)
    {
        return topicMapper.selectByRandom(difficulty,question_id,num);
    }

    //查询所有题目的名字
    public ArrayList<Topic> selectAlltopicName()
    {
        return topicMapper.selectAll();
    }
    //删除 题目
    public void deleteByid(int id)
    {
        topicMapper.deleteTopicByid(id);
    }

    //通过 题目id 查找题目
    public Topic selectByTopicid(int topic_id)
    {
        return topicMapper.selectByTopicid(topic_id);
    }

    //通过题目id 查找错题
    public ErrorTopic selectByErrorTopic(int id)
    {
        return topicMapper.selectError(id);
    }

    //收藏题目 展示
    public CollectAndTopic selectCollect(int id){
        return topicMapper.selectCollect(id);
    }
}
