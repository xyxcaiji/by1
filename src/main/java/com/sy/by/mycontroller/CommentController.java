package com.sy.by.mycontroller;

import com.sy.by.bean.Comment;
import com.sy.by.bean.User;
import com.sy.by.myservice.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    CommentService commentService;

    @ResponseBody
    @GetMapping("/selectAllPid1")
    public ArrayList<Comment> selectAllPid1()
    {
        ArrayList<Comment> comments = commentService.selectAllPID1();
        if(comments.size()==0)
        {
            return null;
        }
        return comments;
    }

    //增加留言
    @ResponseBody
    @GetMapping("/insertComment")
    public String insertComment(Comment comment, HttpServletRequest hsr)
    {
        //初数步  讲系统目前的时间加入到comment字段里面，代表评论的时间
        comment.setCreat_time(new Date());
        //第一步，将用户加到 评论的用户字段里
        User user = (User)hsr.getSession().getAttribute("user");
//         comment.setUser_id(user.getUser_id());
         comment.setUser_name(user.getUser_name());
//         //第二步，判断 pid为多少
//        if(comment.getReply_name()==null)
//        {
//            comment.setPid(1);
//        }else{
//            comment.setPid(2);
//        }

        try {
            commentService.insert(comment);
            commentService.uodatePid(comment.getComment_id());
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }
    //前台传来 pid，回复人的id，name，以及回复内容
    //回复留言
    @ResponseBody
    @GetMapping("/insertComment2")
    public String insertComment2(Comment comment,HttpServletRequest hsr)
    {
        //初数步  讲系统目前的时间加入到comment字段里面，代表评论的时间
        comment.setCreat_time(new Date());
        //第一步，将用户加到 评论的用户字段里
        User user = (User)hsr.getSession().getAttribute("user");
//         comment.setUser_id(user.getUser_id());
        comment.setUser_name(user.getUser_name());
        try {
            commentService.insert(comment);
//            commentService.uodatePid(comment.getComment_id());
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }

    @ResponseBody
    @GetMapping("/selectAppPID2")
    public ArrayList<Comment> selectAppPID2(int comment_id)
    {
        ArrayList<Comment> comments = commentService.selectAllPID2(comment_id);
        return comments;
    }

    @ResponseBody
    @GetMapping("/deleteComment")
    public String deleteComment(int comment_id)
    {
        try {
            commentService.deleteComment(comment_id);
        } catch (Exception e) {
            System.out.println("删除失败");
            return "0";
        }
        return "1";
    }


}
