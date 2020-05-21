package com.sy.by.mycontroller;

import com.sy.by.bean.*;
import com.sy.by.myservice.CollectService;
import com.sy.by.myservice.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

@Controller
public class CollectController {

    @Autowired
    CollectService collectService;
    @Autowired
    TopicService topicService;

    //查询所有的收藏
    @ResponseBody
    @GetMapping("/selectAllCollect")
    public ArrayList<CollectAndTopic> selectAll(HttpServletRequest hrq)
    {
        User user= (User)hrq.getSession().getAttribute("user");
        ArrayList<CollectAndTopic> collectAll=new ArrayList<>();
        try {
            ArrayList<Collect> collects = collectService.selectAllCollectByUser(user.getUser_id());
            Iterator<Collect> iterator = collects.iterator();
            while (iterator.hasNext())
            {
                //然后遍历每一个错误，通过错误的题目id找到对应的题目，并且加入 一个集合里面，返回到前台
                Collect collect = iterator.next();
                int topic_id = collect.getTopic_id();
                CollectAndTopic collectAndTopic = topicService.selectCollect(topic_id);
                int collect_id = collect.getCollect_id();
                collectAndTopic.setCollect_id(collect_id);
                collectAll.add(collectAndTopic);
            }
            return collectAll;
        } catch (Exception e) {
            System.out.println("查找所有收藏失败");
            return null;
        }
    }

    //增加收藏
    @ResponseBody
    @GetMapping("/insertCollect")
    public int insertCollect(@RequestParam("topic_id") int topic_id,HttpServletRequest hrq)
    {
        boolean insert=true;
        User user= (User)hrq.getSession().getAttribute("user");


        Iterator<Collect> iterator = collectService.selectAllCollectByUser(user.getUser_id()).iterator();
        while (iterator.hasNext())
        {
            if(iterator.next().getTopic_id()==topic_id)
            {
                insert=false;
            }
        }
        if(insert==false)
        {
            //因为已经收藏过了
            return 0;
        }
        Collect collect= new Collect();
        collect.setTopic_id(topic_id);
        collect.setUser_id(user.getUser_id());
        try {
            collectService.insertCollect(collect);
            return 1;
        } catch (Exception e) {
            //增加失败
            return -1;
        }
    }

    //删除收藏
    @ResponseBody
    @GetMapping("/deleteCollect")
    public int deleteCollect(@RequestParam("collect_id") int collect_id,HttpServletRequest hrq)
    {
        boolean delete=false;
        User user= (User)hrq.getSession().getAttribute("user");
        Iterator<Collect> iterator = collectService.selectAllCollectByUser(user.getUser_id()).iterator();
        while (iterator.hasNext())
        {
            if(iterator.next().getCollect_id()==collect_id)
            {
                delete=true;
                break;
            }
        }
        if(delete)
        {
            Collect collect=new Collect();
            collect.setUser_id(user.getUser_id());
            collect.setCollect_id(collect_id);
            collectService.deleteCollect(collect);
            return 1;
        }
        return 0;
    }
}
