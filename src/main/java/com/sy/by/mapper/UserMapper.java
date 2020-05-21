package com.sy.by.mapper;

import com.sy.by.bean.User;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface UserMapper {
    @Options(useGeneratedKeys = true,keyProperty = "user_id")
    @Insert("insert into user(user_name,user_password,user_email) values(#{user_name},#{user_password},#{user_email})")
    void insertUser(User user);

    @Select("select * from user")
    ArrayList<User> selectAll();
    @Update("update user set user_password=#{user_password} where user_name=#{user_name}")
    void updatePassword(User user);



    @Update("update user set user_name=#{user_name},user_password=#{user_password},user_email=#{user_email} where user_id=#{user_id}")
    void updateUser(User user);

    @Delete("delete from user  where user_id=#{user_id}")
    void deleteUser(int user_id);

    @Select("select * from user where user_id=#{user_id}")
    User selectByid(int user_id);

    /**
     *  private int user_id;
     *     private String user_name;
     *     private String user_password;
     *     private String user_email;
     *     private String user_head;
     *     @JsonFormat(pattern = "yyyy-MM-dd",timezone="GMT+8")
     *     @DateTimeFormat(pattern = "yyyy-MM-dd")
     *     private Date user_birth;
     *     private String user_school;
     *     private String user_major;
     *     private String user_edu;
     *     private String user_brief;
     *     private String user_sex;
     *     private String user_company;
     *     private String user_position;
     * @param user
     */

    @Update("update user set user_head=#{user_head},user_name=#{user_name},user_sex=#{user_sex},user_birth=#{user_birth},user_email=#{user_email},user_position=#{user_position},user_company=#{user_company},user_edu=#{user_edu},user_school=#{user_school},user_major=#{user_major},user_brief=#{user_brief} where user_id=#{user_id}")
    void updateU(User user);
}
