package com.sy.by.mapper;

import com.sy.by.bean.Wen_answer;
import com.sy.by.bean.Wen_juan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WenJuanMapper {
    @Options(useGeneratedKeys = true,keyProperty = "wen_juan_id")
    @Insert("insert into wen_juan(juan_content,juan_zhuti) values(#{juan_content},#{juan_zhuti})")
    void insertJuan(Wen_juan wen_juan);

    @Select("select wen_juan_id,juan_zhuti from wen_juan")
    List<Wen_juan> selectAllWen();

    @Select("select * from wen_answer")
    List<Wen_answer> selectAllWenAnswer();

    /**
     *
     * @param wen_answer
     *  private String sex;
     *    private String a;
     *    private String b;
     *    private String c;
     *    private String d;
     *    private String e;
     *    private String suggestion;
     *    private String[] e1;
     */
    @Insert("insert into wen_answer(sex,a,b,c,d,e,suggestion,user_name) values(#{sex},#{a},#{b},#{c},#{d},#{e},#{suggestion},#{user_name})")
    void insert(Wen_answer wen_answer);

    @Select("select user from wen_answer")
    List<String> selectAllUser_name();
}
