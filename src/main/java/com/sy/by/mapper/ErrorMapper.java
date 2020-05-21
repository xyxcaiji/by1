package com.sy.by.mapper;
/**
 *  private int error_id;
 *     private int topic_id;
 *     private int subject_id;
 *     private String subject_name;
 */

import com.sy.by.bean.Errorr;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Mapper
public interface ErrorMapper {

    //向错误表中增加数据
    @Options(useGeneratedKeys = true,keyProperty = "error_id")
    @Insert("insert into errorr(topic_id,subject_id,subject_name,user_id) values(#{topic_id},#{subject_id},#{subject_name},#{user_id})")
    void insertError(Errorr errorr);

    //查询表中的数据
    @Select("select * from errorr where subject_name=#{subject_name} and user_id=#{user_id}")
    ArrayList<Errorr> selectBySubjectName(@Param("subject_name") String subject_name, @Param("user_id") int user_id);

    //查询所有
    @Select("select * from errorr where user_id=#{user_id}")
    ArrayList<Errorr> selectAll(int user_id);

    //通过 错题id查看错题在不在 错题表里
    @Select("select * from errorr where user_id=#{user_id} and topic_id=#{topic_id}")
    Errorr selectByTopicId(@Param("user_id")int user_id,@Param("topic_id")int topic_id);

}
