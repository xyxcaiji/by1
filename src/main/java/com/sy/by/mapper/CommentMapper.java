package com.sy.by.mapper;

import com.sy.by.bean.Comment;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

/**
 *     private int comment_id;
 *     private int user_id;
 *     private int pid;
 *     private String user_name;
 *     private String comment_msg;
 *     private Date creat_time;
 */
@Mapper
public interface CommentMapper {

    @Select("select * from comment where pid=comment_id order by creat_time desc")
    ArrayList<Comment> selectAllPID1();
    @Select("select * from comment where pid=#{comment_id} and pid!=comment_id order by creat_time desc")
    ArrayList<Comment> selectAllPID2AndCommentId(int comment_id);

    @Options(useGeneratedKeys = true,keyProperty = "comment_id",keyColumn = "comment_id")
    @InsertProvider(type=CommentInsert.class,method = "insertComment")
    void insertComment(Comment comment);

    //添加级别
    @Update("update comment set pid=#{pid} where comment_id=#{comment_id}")
    void insertPid(@Param("pid") int pid,@Param("comment_id") int comment_id);
    //删除
    @Delete("delete from comment where comment_id=#{comment_id}")
    void deleteComment(int comment_id);

    @Select("select * from comment where user_name=#{user_name} and pid=comment_id order by creat_time desc")
    List<Comment> selectByUser(String user_name);
}
