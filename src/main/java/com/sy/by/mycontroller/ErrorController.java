package com.sy.by.mycontroller;

import com.sy.by.bean.ErrorTopic;
import com.sy.by.bean.Errorr;
import com.sy.by.bean.Topic;
import com.sy.by.bean.User;
import com.sy.by.myservice.ErrorService;
import com.sy.by.myservice.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;

@Controller
public class ErrorController {

    @Autowired
    ErrorService errorService;
    @Autowired
    TopicService topicService;

    //查看错题
    //并且 判断错误，顺便发回去正确的给前台
    @ResponseBody
    @GetMapping("/selectAllErrorr")
    public ArrayList<ErrorTopic> judge(HttpServletRequest hrq/*,@RequestParam("user_id") int uesr_id*/)
    {
        ArrayList<Errorr> errorrs=new ArrayList<>();
        ArrayList<ErrorTopic> topics=new ArrayList<>();
        User answer = (User)hrq.getSession().getAttribute("user");
        try {
            //如果找到了  证明这个用户有错误，返回错误的集合
            errorrs = errorService.selectAll(answer.getUser_id()/*uesr_id*/);
            Iterator<Errorr> iterator = errorrs.iterator();
            while (iterator.hasNext())
            {
                //然后遍历每一个错误，通过错误的题目id找到对应的题目，并且加入 一个集合里面，返回到前台
                Errorr next = iterator.next();
                int topic_id = next.getTopic_id();
                ErrorTopic topic = topicService.selectByErrorTopic(topic_id);
                topics.add(topic);
            }
            return topics;
        } catch (Exception e){
            //如果报异常了
            if(errorrs==null)
            return null;
        }
            return null;
    }

}
