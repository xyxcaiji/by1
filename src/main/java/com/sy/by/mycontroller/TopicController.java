package com.sy.by.mycontroller;

import com.sy.by.bean.*;
import com.sy.by.myservice.ErrorService;
import com.sy.by.myservice.Question_bankService;
import com.sy.by.myservice.Test_paperService;
import com.sy.by.myservice.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TopicController {

    @Autowired
    TopicService topicService;

    @Autowired
    ErrorService errorService;

    @Autowired
    Question_bankService question_bankService;

    @Autowired
    Test_paperService test_paperService;

    //判断题目错误与正确，并且返回错误与正确的值
    @ResponseBody
    @GetMapping("/TopicJudge")
    public boolean[] rightAndWrong(@RequestParam(value = "answers[]",required = false) char[] answers,@RequestParam("test_id")int test_id,HttpServletRequest hrq)
    {
        Map<Integer,Integer> map=new HashMap<>();

        //从session中拿出之前在  randomTopic随机抽取题目的时候存到session的答案和topicId
        //与前台传会爱的答案进行判断，并且返回结果给前台
        User user = (User)hrq.getSession().getAttribute("user");
        System.out.println(user.toString());
        int user_id=user.getUser_id();
        System.out.println(user_id+"user_id.........");
        char[] answer = (char[])hrq.getSession().getAttribute("answer");
        int[] topicId = (int[])hrq.getSession().getAttribute("topicId");
        int[] subjectId=(int[])hrq.getSession().getAttribute("subjectId");
        hrq.getSession().getAttribute("topicId");
        System.out.println(Arrays.toString(answer));
        System.out.println(Arrays.toString(answers));
        boolean[] rAndw = new boolean[answer.length];
        for(int i=0;i<answer.length;i++)
        {
            if(answer[i]==answers[i])
            {
                rAndw[i]=true;
            }else {
                rAndw[i]=false;
                map.put(topicId[i],subjectId[i]);
            }
        }
        //将错误的加入到 我的错误里面
        Iterator<Integer> iterator = map.keySet().iterator();
        while (iterator.hasNext())
        {
            Integer topic = iterator.next();
            if(errorService.selectByTopicId(user_id,topic)==null)
            {
                Errorr errorr=new Errorr();
                errorr.setTopic_id(topic);
                errorr.setSubject_id(map.get(topic));
                String subject = question_bankService.selectByid(map.get(topic)).getSubject();
                errorr.setSubject_name(subject);
                errorr.setUser_id(user_id);
                errorService.insertError(errorr);
            }
        }

        try {
            Test_paper test_paper = new Test_paper();
            test_paper.setTest_id(test_id);
            test_paper.setUser_id(user_id);
            StringBuilder sb1=new StringBuilder();
            for(int i=0;i<answers.length;i++)
            {
                if(i==answers.length-1) sb1.append(answers[i]);
                else {
                    sb1.append(answers[i]+",");
                }
            }
            test_paper.setAnswers(sb1.toString());
            System.out.println(sb1.toString()+"answers++++");
            StringBuilder sb2=new StringBuilder();
            for(int j=0;j<rAndw.length;j++)
            {
                if(j==rAndw.length-1) sb1.append(rAndw[j]);
                else {
                    sb2.append(rAndw[j]+",");
                }
            }
            test_paper.setR_and_w(sb2.toString());
            System.out.println(sb2.toString()+"randw+++");
            test_paper.setTest_id(test_id);
            System.out.println(test_id+"tteesssdd");
            test_paperService.updateTest_paper(test_paper);
        } catch (Exception e) {
            System.out.println("更新我的卷子时候出错");
        }

        //用完之后  删掉之前存在session里面的答案，不要浪费资源
//        hrq.getSession().removeAttribute("answer");
        return rAndw;
    }

    //增加 题目
    @ResponseBody
    @GetMapping("/insertTopic")
    public String insertTopic(Topic topic)
    {
        boolean b=true;
        ArrayList<Topic> topics=null;
        try {
            topics = topicService.selectAlltopicName();
            for(Topic name:topics)
            {
                if(topic.getTopic_content().equals(name.getTopic_content())) b=false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(b)
        {
            topicService.insertTopic(topic);
            return "1";
        }
        return "0";
    }
    //删除 题目 通过题目id
    @ResponseBody
    @GetMapping("/deleteTopicByid")
    public String deleteTopicById(int id)
    {
        boolean b=false;
        ArrayList<Topic> topics=null;
        try {
            topics = topicService.selectAlltopicName();
            for(Topic name:topics)
            {
                if(name.getId()==id) b=true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(b)
        {
            topicService.deleteByid(id);
            return "1";
        }
        return "0";
    }
    //更新
    @ResponseBody
    @GetMapping("/updateTopic")
    public String updateTopic(Topic topic)
    {
        if(topic.getTopic_content()==null || topic.getAnswer()==0 || topic.getA()==null || topic.getB()==null || topic.getC()==null || topic.getD()==null || topic.getSection_id()==0 || topic.getQuestion_id()==0 || topic.getTopic_type()==null)
        {
            return "0";
        }
        topicService.updateTopic(topic);
        return "1";
    }

    //通过 章节id 查找 所有题目
    @ResponseBody
    @GetMapping("/selectAllByTopicId")
    public ArrayList<Topic> selectAllByTopicId(int id)
    {
        ArrayList<Topic> topics = null;
        try {
            topics=topicService.selectBySectionId(id);
        } catch (Exception e) {
            //查无次 章节
            return null;
        }
        //如果有 就返回
        return topics;
    }

    //随机抽取题目
    @ResponseBody
    @GetMapping("/selectByRandom")
    public synchronized ArrayList byRandomDifficult(@RequestParam("difficulty")String difficulty, @RequestParam("question_id")int question_id, HttpServletRequest hsRequest)
    {
        //简单的集合
        ArrayList<Topic> easyS=null;
        //普通的集合
        ArrayList<Topic> ordinaryS = null;
        //困难的集合
        ArrayList<Topic> difficultS = null;
        if(difficulty.equals("简单"))
        {
            easyS = topicService.selectByRandom("简单", 15,question_id);
            ordinaryS = topicService.selectByRandom("普通", 4,question_id);
            difficultS = topicService.selectByRandom("困难", 1,question_id);
        }
        else if(difficulty.equals("普通"))
        {
            easyS = topicService.selectByRandom("简单", 3,question_id);
            ordinaryS = topicService.selectByRandom("普通", 13,question_id);
            difficultS = topicService.selectByRandom("困难", 4,question_id);
        }
        else if(difficulty.equals("困难"))
        {
            easyS = topicService.selectByRandom("简单", 4,question_id);
            ordinaryS = topicService.selectByRandom("普通", 8,question_id);
            difficultS = topicService.selectByRandom("困难", 8,question_id);
        }

        ArrayList topics = new ArrayList<>();
        topics.addAll(easyS);
        topics.addAll(ordinaryS);
        topics.addAll(difficultS);
        //得到这些随即题目的答案  存到session 因为要判断他们答得对不对
        char[] chars=new char[topics.size()];
        int[] topicId=new int[topics.size()];
        int[] subjectId=new int[topics.size()];
        int i=0;
        Iterator iterator = topics.iterator();
        while (iterator.hasNext())
        {
            Topic next = (Topic)iterator.next();
            //将答案保存在 chars 数组
            chars[i] = next.getAnswer();
            topicId[i] = next.getId();
            subjectId[i] = next.getQuestion_id();
            i++;
        }
        //将答案结果保存在session
        //还有topicId题目id
        //还有科目id
        hsRequest.getSession().setAttribute("answer",chars);
        hsRequest.getSession().setAttribute("topicId",topicId);
        hsRequest.getSession().setAttribute("subjectId",subjectId);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd dd:HH:mm:ss");
        String format = simpleDateFormat.format(new Date());

        ArrayList list = new ArrayList();
        list.addAll(topics);
//        list.add(format);

        //然后将这些题目加入到我的考试里面，因为有个功能是查看我之前的考试卷子，所以要在这个时候加进去
        Test_paper test_paper=new Test_paper();
        test_paper.setTest_date(new Date());
        //加入课目名字
        test_paper.setSubject_name(question_bankService.selectByid(question_id).getSubject());
        //将 题目id拼成一个字符串加入到数据库
        StringBuilder sb = new StringBuilder();
        for(int j=0;j<topicId.length;j++)
        {
            if(j==topicId.length-1) sb.append(topicId[j]);
            else {
                sb.append(topicId[j]+",");
            }
        }
        test_paper.setTopics_id(sb.toString());
        //将这些数据加入到 试卷中
        test_paperService.insertTest_paper(test_paper);
        list.add(test_paper.getTest_id());
        System.out.println(test_paper.getTest_id()+"试卷的id");

        return list;
    }


}
