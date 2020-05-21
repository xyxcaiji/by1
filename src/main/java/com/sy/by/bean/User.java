package com.sy.by.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

/**
 * 对应数据库用户表
 */
public class User {
    private int user_id;
    private String user_name;
    private String user_password;
    private String user_email;
    private String user_head;
    @JsonFormat(pattern = "yyyy-MM-dd"/*,timezone="GMT+8"*/)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date user_birth;
    private String user_school;
    private String user_major;
    private String user_edu;
    private String user_brief;
    private String user_sex;
    private String user_company;
    private String user_position;

    private String yan;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public String getUser_email() {
        return user_email;
    }

    public void setUser_email(String user_email) {
        this.user_email = user_email;
    }

    public String getUser_head() {
        return user_head;
    }

    public void setUser_head(String user_head) {
        this.user_head = user_head;
    }

    public Date getUser_birth() {
        return user_birth;
    }

    public void setUser_birth(Date user_birth) {
        this.user_birth = user_birth;
    }

    public String getUser_school() {
        return user_school;
    }

    public void setUser_school(String user_school) {
        this.user_school = user_school;
    }

    public String getUser_major() {
        return user_major;
    }

    public void setUser_major(String user_major) {
        this.user_major = user_major;
    }

    public String getUser_edu() {
        return user_edu;
    }

    public void setUser_edu(String user_edu) {
        this.user_edu = user_edu;
    }

    public String getUser_brief() {
        return user_brief;
    }

    public void setUser_brief(String user_brief) {
        this.user_brief = user_brief;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getUser_company() {
        return user_company;
    }

    public void setUser_company(String user_company) {
        this.user_company = user_company;
    }

    public String getUser_position() {
        return user_position;
    }

    public void setUser_position(String user_position) {
        this.user_position = user_position;
    }

    public String getYan() {
        return yan;
    }

    public void setYan(String yan) {
        this.yan = yan;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_head='" + user_head + '\'' +
                ", user_birth=" + user_birth +
                ", user_school='" + user_school + '\'' +
                ", user_major='" + user_major + '\'' +
                ", user_edu='" + user_edu + '\'' +
                ", user_brief='" + user_brief + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", user_company='" + user_company + '\'' +
                ", user_position='" + user_position + '\'' +
                ", yan='" + yan + '\'' +
                '}';
    }
}
