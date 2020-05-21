package com.sy.by.mapper;
/**
 * 科目表 与 数据库进行增删改查
 */

import com.sy.by.bean.Question_bank;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface Question_bankMapper {
    //查询全部
    @Select("select * from question_bank")
    ArrayList<Question_bank> selectAllquestion();

    //查询科目id以及 科目名称
    @Select("select id,subject from question_bank")
    ArrayList<Question_bank> selectAllIdAndSubject();


    //查询单个
    @Select("select * from question_bank where id=#{id}")
    Question_bank selectByid(int id);


    //增加科目
    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into question_bank(subject,brief,subject_pic) values(#{subject},#{brief},#{subject_pic})")
    void insertQuestion_bank(Question_bank question_bank);
    //删除科目
    @Delete("delete from question_bank where id=#{id}")
    void delete(int id);

    //动态sql语句，你更新哪个，我就给数据库传哪个
//    @Update("<script>" +
//            "UPDATE `question_bank` " +
//            "<set>" +
//            "<if test=\"subject != null\">" +
//            " subject = #{subject}," +
//            "</if> " +
//            "<if test=\"brief != null\">" +
//            " brief = #{brief}," +
//            "</if> " +
//            "</set>" +
//            " WHERE id = #{id}" +
//            "</script>")
    @Update("update question_bank set subject=#{subject},brief=#{brief},subject_pic=#{subject_pic} where id=#{id}")
    void update(Question_bank question_bank);
}
