package com.sy.by.mycontroller;

import com.sy.by.bean.Comment;
import com.sy.by.bean.User;
import com.sy.by.myservice.CommentService;
import com.sy.by.myservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserConroller {

    @Autowired
    UserService userService;

    @Autowired
    CommentService commentService;

    //查找该用户
    @ResponseBody
    @GetMapping("/selectUserById")
    public User selectUser(HttpServletRequest hrq)
    {
        User user = null;
        try {
            //为什么从session中取出来数据还要再从数据库中取出来，因为 修改过，所以要从新再去
            user = (User)hrq.getSession().getAttribute("user");
            User user1 = userService.selectByid(user.getUser_id());
            System.out.println(user1.toString());
            return user1;
        } catch (Exception e) {
            System.out.println("查找用户失败");
            return null;
        }
    }

    @ResponseBody
    @PostMapping(value="/customUser",consumes = "application/json")
    public String updateU(@RequestBody User user, HttpServletRequest hrq)
    {
        User u = (User)hrq.getSession().getAttribute("user");
        System.out.println(user.toString());
        user.setUser_id(u.getUser_id());
        try {
            userService.updateU(user);
            return "1";
        } catch (Exception e) {
            System.out.println("自定义信息失败");
            return null;
        }
    }

    @ResponseBody
    @GetMapping("/userrrs")
    public ArrayList<User> selectAll()
    {
        ArrayList<User> users = userService.selectAll();
        return users;
    }

    @ResponseBody
    @GetMapping("/updateStudents")
    public String update(User user)
    {
        try {
            String s = userService.selectByid(user.getUser_id()).getUser_name();
            userService.uodateStudent(user);
        } catch (Exception e) {
            return "0";
        }
        return "1";
    }

    //
//    //删除学生
    @ResponseBody
    @GetMapping(value = "/deleteUser")
    public String deleteByid(int user_id)
    {

        try {
            //先查有没有
            User students = userService.selectByid(user_id);
            userService.deleteUser(user_id);
            return "1";
        } catch (Exception e) {
            e.printStackTrace();
            return "0";
        }
    }

    //查看我的所有留言
    @ResponseBody
    @GetMapping("/selectComment")
    public List<Comment> selectComment(HttpServletRequest hrq)
    {
        User user = (User)hrq.getSession().getAttribute("user");
        return commentService.selectAllMSGUser(user.getUser_name());
    }

}
