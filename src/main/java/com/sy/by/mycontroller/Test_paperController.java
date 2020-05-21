package com.sy.by.mycontroller;

import com.sy.by.bean.Test_paper;
import com.sy.by.bean.Topic;
import com.sy.by.bean.User;
import com.sy.by.myservice.Test_paperService;
import com.sy.by.myservice.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class Test_paperController {
    @Autowired
    Test_paperService test_paperService;

    @Autowired
    TopicService topicService;
    //单个查询
    @ResponseBody
    @GetMapping("/selectTestByTest_id")
    public Map selectByTest_id(@RequestParam("test_id") int test_id)
    {
        List topics=new ArrayList();
        List answers=new ArrayList();
        List rAndw=new ArrayList();
        Map map = new HashMap();
        try {
            Test_paper test_paper = test_paperService.selectByTest_id(test_id);
            String topics_id = test_paper.getTopics_id();
            String[] split = topics_id.split(",");
            for(int i=0;i<split.length;i++)
            {
                Topic topic = topicService.selectByTopicid(Integer.parseInt(split[i]));
                topics.add(topic);
            }
            String[] split1 = test_paper.getAnswers().split(",");
            answers.add(split1);
            String[] split2 = test_paper.getR_and_w().split(",");
            rAndw.add(split2);
            map.put("topics",topics);
            map.put("answers",answers);
            map.put("rAndw",rAndw);
            System.out.println(map+"..");
            return map;
        } catch (Exception e) {
            System.out.println("单个查询卷子失败");
            return null;
        }
    }
    //查询某个用户的所有
    @ResponseBody
    @GetMapping("/selectTestByUser_id")
    public ArrayList<Test_paper> selectByUser_id(HttpServletRequest hrq/*,@RequestParam("user_id")int user_id*/)
    {
        User user = (User)hrq.getSession().getAttribute("user");
        System.out.println(user.toString());
        int user_id=user.getUser_id();
        try {
            ArrayList<Test_paper> test_papers = test_paperService.selectAllByUser_id(user_id);
            return test_papers;
        } catch (Exception e) {
            System.out.println("查询该用户所有试卷失败");
            return null;
        }
    }
}
