package com.sy.by.mapper;
/**
 *     private int test_id;
 *     private String topics_id;
 *     private String answers;
 *     private String r_and_w;
 *     private int user_id;
 *      private Date test_date;
 *     private String subject_name;
 */

import com.sy.by.bean.Test_paper;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.io.ResolverUtil;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface Test_paperMapper {
    //添加
    @Options(useGeneratedKeys = true,keyProperty = "test_id", keyColumn = "test_id")
    @Insert("insert into test_paper(topics_id,test_date,subject_name) values(#{topics_id},#{test_date},#{subject_name})")
    void insertTest_paper(Test_paper test_paper);

    //更新
    @Update("update test_paper set user_id=#{user_id},answers=#{answers},r_and_w=#{r_and_w} where test_id=#{test_id}")
    void updateTest_paper(Test_paper test_paper);

    //查找某个用户所有的题
    @Select("select test_id,test_date,subject_name from test_paper  where user_id=#{user_id}")
    ArrayList<Test_paper> selectAllByuser_Id(int user_id);
    //查找某一张试卷
    @Select("select * from test_paper where test_id=#{test_id}")
    Test_paper selectByTest_id(int test_id);

}
