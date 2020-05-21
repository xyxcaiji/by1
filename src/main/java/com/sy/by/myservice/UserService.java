package com.sy.by.myservice;

import com.sy.by.bean.User;
import com.sy.by.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    //添加用户
    public void insertUser(User user)
    {
        userMapper.insertUser(user);
    }
    //查询所有的用户
    //判断是否注册的时候用到
    public ArrayList<User> selectAll()
    {
        ArrayList<User> users = userMapper.selectAll();
        return users;
    }

    //更新密码
    public void uodatePassword(User user)
    {
        userMapper.updatePassword(user);
    }

    //删除学生
    public void deleteUser(int user_id)
    {
        userMapper.deleteUser(user_id);
    }
    //更新学生信息
    public void uodateStudent(User user)
    {
        userMapper.updateUser(user);
    }

    //根据id查学生
    public User selectByid(int user_id)
    {
        return userMapper.selectByid(user_id);
    }

    //个人中心自定义信息
    public void updateU(User user)
    {
        userMapper.updateU(user);
    }
}
