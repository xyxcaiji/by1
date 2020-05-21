package com.sy.by.bean;


import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;


/**
 * 留言表
 *
 *
 * 注解@JsonFormat主要是后台到前台的时间格式的转换
 *
 * 注解@DataFormAT主要是前后到后台的时间格式的转换
 */
public class Comment {
    private int comment_id;
    //回复的是哪个comment，用来查找对应的回复用的
    private int reply_com_id;
    //回复的人的名字
    private String reply_name;
//    private int user_id;
    private int pid;
    private String user_name;
    private String comment_msg;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date creat_time;

    public int getComment_id() {
        return comment_id;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

    public String getReply_name() {
        return reply_name;
    }

    public void setReply_name(String reply_name) {
        this.reply_name = reply_name;
    }

    public int getPid() {
        return pid;
    }

    public void setPid(int pid) {
        this.pid = pid;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getComment_msg() {
        return comment_msg;
    }

    public void setComment_msg(String comment_msg) {
        this.comment_msg = comment_msg;
    }

    public Date getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(Date creat_time) {
        this.creat_time = creat_time;
    }

    public int getReply_com_id() {
        return reply_com_id;
    }

    public void setReply_com_id(int reply_com_id) {
        this.reply_com_id = reply_com_id;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "comment_id=" + comment_id +
                ", reply_com_id=" + reply_com_id +
                ", reply_name='" + reply_name + '\'' +
                ", pid=" + pid +
                ", user_name='" + user_name + '\'' +
                ", comment_msg='" + comment_msg + '\'' +
                ", creat_time=" + creat_time +
                '}';
    }
}
