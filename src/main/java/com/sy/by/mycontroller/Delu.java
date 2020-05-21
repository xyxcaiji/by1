package com.sy.by.mycontroller;

import com.sy.by.bean.User;
import com.sy.by.myservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

@Controller
public class Delu {

    @Autowired
    UserService userService;

    //登录
    @ResponseBody
    @PostMapping(value = "/denglu",consumes = "application/json")
//    @GetMapping("/denglu")
    public String dengLu(@RequestBody User user1,HttpServletRequest httpServletRequest)
    {

        String random =(String) httpServletRequest.getServletContext().getAttribute("random");
        System.out.println("登陆验证"+random);
        System.out.println("登录信息:"+user1.toString());
        String yan=user1.getYan();
        System.out.println(yan);
        if(random.equals(yan.toUpperCase())) {
            //用完之后，移除掉，不要浪费资源
            httpServletRequest.getServletContext().removeAttribute("random");
            if(user1.getUser_name().equals("admain")&&user1.getUser_password().equals("admain"))
            {
                return "2";
            }
            List<User> users = userService.selectAll();
            Iterator<User> iterator = users.iterator();
            boolean yonghu = false;
            boolean mima = false;
            while (iterator.hasNext()) {
                User user = iterator.next();
                //如果 验证通过 返回1
                if (user.getUser_name().equals(user1.getUser_name())) {
                    yonghu = true;
                    if (user.getUser_password().equals(user1.getUser_password())) {
                        mima = true;
                        //在session里面存入登录的用户信息，到时候什么时候需要用户信息可以随时取出来
                        httpServletRequest.getSession().setAttribute("user",user);
                        System.out.println(httpServletRequest.getSession().getAttribute("user").toString());
                        return "1";
                    }
                }
            }
            //用户正确，密码不对
            if (yonghu == true && mima == false) {
                return "0";
            }
            //验证失败 返回0
            return "-1";
        }

        return "-2";
    }

    //注册
    @ResponseBody
    @PostMapping(value = "/zhuce",consumes = "application/json")
    public String zhuCe(@RequestBody User user1)
    {
        List<User> users = userService.selectAll();
        Iterator<User> iterator = users.iterator();
        while (iterator.hasNext())
        {
            User user = iterator.next();
            //用户已经存在  返回0
            if(user.getUser_name().equals(user1.getUser_name()))
            {
                return "0";
            }
        }
        //注册成功 返回1
        userService.insertUser(user1);
        return "1";
    }
    //忘记密码
    @ResponseBody
    @PostMapping(value = "/wangMM",consumes = "application/json")
    public String wangMM(@RequestBody User user){
        List<User> users = userService.selectAll();
        boolean temp1=false;
        boolean temp2=false;
        for (User u : users) {
            if (u.getUser_name().equals(user.getUser_name())) {
                temp1=true;
                if (u.getUser_email().equals(user.getUser_email())) {
                    temp2=true;
                    userService.uodatePassword(user); }
            }
        }
        //用户名错误
        if(temp1==false||temp2==false)
        {
            return "0";
        }
        //正确则返回1
        return "1";
    }




}
