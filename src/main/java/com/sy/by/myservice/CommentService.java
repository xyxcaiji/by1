package com.sy.by.myservice;

import com.sy.by.bean.Comment;
import com.sy.by.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    //查询所有初始1级评论
    public ArrayList<Comment> selectAllPID1()
    {
        return commentMapper.selectAllPID1();
    }
    //查咋在评论中的回复，也就是2级评论
    public ArrayList<Comment> selectAllPID2(int comment_id)
    {
        return commentMapper.selectAllPID2AndCommentId(comment_id);
    }
    //增加评论
    public void insert(Comment comment)
    {
        commentMapper.insertComment(comment);
    }

    //添加级别
    public void uodatePid(int pid){
        commentMapper.insertPid(pid,pid);
    }
    //删除评论
    public void deleteComment(int comment_id){
        commentMapper.deleteComment(comment_id);
    }
    //查看某个用户所有评论
    public List<Comment> selectAllMSGUser(String user_name)
    {
        return commentMapper.selectByUser(user_name);
    }
}
