package com.sy.by.mapper;
/**
 *  private int id;
 *     private String topic_type;
 *     private String topic_content;
 *     private String A;
 *     private String B;
 *     private String C;
 *     private String D;
 *     private char answer;
 *     private String difficulty;
 *     //所属的课程id
 *     private int question_id;
 *     //所属的章节id
 *     private int section_id;
 */

import com.sy.by.bean.CollectAndTopic;
import com.sy.by.bean.ErrorTopic;
import com.sy.by.bean.Topic;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Mapper
public interface TopicMapper {
    //添加
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into topic(topic_type,topic_content,A,B,C,D,answer,difficulty,question_id,section_id) values(#{topic_type},#{topic_content},#{A},#{B},#{C},#{D},#{answer},#{difficulty},#{question_id},#{section_id})")
    void insertTopic(Topic topic);

    @Select("select  id,topic_content from topic")
    ArrayList<Topic> selectAll();

    //随机抽取 不同难度的题
    @Select("SELECT * FROM topic where difficulty=#{difficulty} and question_id=#{question_id} order by rand() limit #{num}")
    ArrayList<Topic> selectByRandom(@Param("difficulty") String difficulty,@Param("question_id") int question_id,@Param("num") int num);

    //通过章节查找对应的题目
    @Select("select * from topic where section_id=#{section_id}")
    ArrayList<Topic> selectBySectionId(int section_id);
    //删除题目 通过题目id
    @Delete("delete from topic where id=#{id}")
    void deleteTopicByid(int id);

    //更新题目表
    @UpdateProvider(type = UpdateTopic.class,method = "updateTopic")
    void updateTopic(Topic topic);

    //通过题目id 查找题目
    @Select("select * from topic where id=#{topic_id}")
    Topic selectByTopicid(int topic_id);

    @Select("select t.id,t.topic_type,t.topic_content,t.A,t.B,t.C,t.D,t.answer,t.difficulty,s.subject_name,s.section_name from topic t,section s where t.id=#{id} and  t.section_id=s.section_id")
    ErrorTopic selectError(int id);

    @Select("select t.id,t.topic_type,t.topic_content,t.A,t.B,t.C,t.D,t.answer,t.difficulty,s.subject_name,s.section_name from topic t,section s where t.id=#{id} and  t.section_id=s.section_id")
    CollectAndTopic selectCollect(int id);

}
