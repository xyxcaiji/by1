package com.sy.by.mapper;

import com.sy.by.bean.Comment;
import org.apache.ibatis.jdbc.SQL;
/**
 *     private int comment_id;
 *     private int user_id;
 *     private int pid;
 *     private String user_name;
 *     private String comment_msg;
 *     private Date creat_time;
 *      private int reply_com_id;
 *     //回复的人的名字
 *     private String reply_name;
 *
 */
public class CommentInsert {
    public String insertComment(final Comment comment)
    {
        return new SQL(){
            {
                INSERT_INTO("comment");
//                INTO_COLUMNS("user_id","user_name","comment_msg","creat_time");
//                INTO_VALUES("#{user_id},#{user_name},#{comment_msg},#{creat_time}");
                if(comment.getComment_msg()!=null)
                {
                    VALUES("comment_msg","#{comment_msg}");
                    VALUES("creat_time","#{creat_time}");
//                    VALUES("user_id","#{user_id}");
                    VALUES("user_name","#{user_name}");

                }
                if(comment.getReply_name()!=null)
                {
                    VALUES("reply_name","#{reply_name}");
                    VALUES("reply_com_id","#{reply_com_id}");
                    VALUES("pid","#{pid}");
                }
            }
        }.toString();
    }
}
