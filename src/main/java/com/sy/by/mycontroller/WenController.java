package com.sy.by.mycontroller;

import com.sy.by.bean.User;
import com.sy.by.bean.Wen_answer;
import com.sy.by.bean.Wen_juan;
import com.sy.by.myservice.WenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class WenController {
    @Autowired
    WenService wenService;

//    //增加问卷题目
//    @ResponseBody
//    @GetMapping("/insertWemJuan")
//    public String insertWenJuan(Wen_juan wen_juan)
//    {
//        wenService.insertWenJuan(wen_juan);
//        return "1";
//    }
//    //查找所有问卷
//    @ResponseBody
//    @GetMapping("/selectAllWenJuan")
//    public List<Wen_juan> selectAll()
//    {
//        return wenService.selectAllWen();
//    }
//    //增加答案
    @ResponseBody
    @PostMapping(value = "/insertAnswer",consumes = "application/json")
    public String insertAnswer(@RequestBody Wen_answer wen_answer, HttpServletRequest hrq)
    {
        System.out.println("问卷的答案："+wen_answer);
        User user= (User)hrq.getSession().getAttribute("user");
            String[] e1 = wen_answer.getE1();
            StringBuffer sb1=new StringBuffer();
            for(int i=0;i<e1.length;i++)
            {
                if(i==e1.length-1)
                {
                    sb1.append(e1[i]);
                }else {
                    sb1.append(e1[i]+":");
                }
            }
            wen_answer.setUser_name(user.getUser_name());
            wen_answer.setE(sb1.toString());
            wen_answer.setE1(null);
            wenService.insertWenAnswer(wen_answer);
        return "1";
    }

    /**
     * @return
     *
     * 通过问卷id，得到对应的问卷的所有答案，然后将
     * 选择 是  的存到 answerMap 这个map集合
     * 选择 否  的存到 falseMap  这个集合
     *
     * 最后 将上面两个集合加到 List<Map<Integer, Integer>> answerAll  返回
     */
    //查找答案
    @ResponseBody
    @GetMapping("/selectAnswer")
    public HashMap selectAllAnswer()
    {
        HashMap hashMap=new HashMap();
        int[] v1=new int[2];
        int[] v2=new int[5];
        int[] v3=new int[2];
        int[] v4=new int[4];
        int[] v5=new int[2];
        int[] v6=new int[4];
        List<String> v7=new ArrayList<>();
        List<Wen_answer> wen_answers = null;
        try {
            wen_answers = wenService.selectAllAnswer();
        } catch (Exception e) {
            return null;
        }
        for(int i=0;i<wen_answers.size();i++)
        {
            Wen_answer wen_answer = wen_answers.get(i);
            String sex = wen_answer.getSex();
            if(sex.equals("男"))
            {
                v1[0]+=1;
            }else if(sex.equals("女"))
            {
                v1[1]+=1;
            }

            String a = wen_answer.getA();
            if(a.equals("非常好"))
            {
                v2[0]+=1;
            }else if(a.equals("挺好"))
            {
                v2[1]+=1;
            }
            else if(a.equals("一般"))
            {
                v2[2]+=1;
            }
            else if(a.equals("不好"))
            {
                v2[3]+=1;
            }
            else if(a.equals("非常差"))
            {
                v2[4]+=1;
            }

            String b = wen_answer.getB();
            if(b.equals("有"))
            {
                v3[0]+=1;
            }else if(b.equals("没有"))
            {
                v3[1]+=1;
            }

            String c = wen_answer.getC();
            if(c.equals("合理"))
            {
                v4[0]+=1;
            }else if(c.equals("不合理"))
            {
                v4[1]+=1;
            }
            else if(c.equals("偏难"))
            {
                v4[2]+=1;
            }
            else if(c.equals("偏易"))
            {
                v4[3]+=1;
            }

            String d = wen_answer.getD();
            if(d.equals("愿意"))
            {
                v5[0]+=1;
            }else if(d.equals("不愿意"))
            {
                v5[1]+=1;
            }

            String[] e = wen_answer.getE().split(":");
            for(int j=0;j<e.length;j++)
            {
                if(e[j].equals("功能齐全"))
                {
                    v6[0]+=1;
                }else if(e[j].equals("组成试卷速度快"))
                {
                    v6[1]+=1;
                }
                else if(e[j].equals("试卷难易程度合理"))
                {
                    v6[2]+=1;
                }
                else if(e[j].equals("能有效检测学习情况"))
                {
                    v6[3]+=1;
                }
            }

            String suggestion = wen_answer.getSuggestion();
            if(!suggestion.equals("无"))
            {
                v7.add(suggestion);
            }
        }
        hashMap.put("people",wen_answers.size());
        hashMap.put("a",v1);
        hashMap.put("b",v2);
        hashMap.put("c",v3);
        hashMap.put("d",v4);
        hashMap.put("e",v5);
        hashMap.put("f",v6);
        hashMap.put("g",v7);

        return hashMap;
    }

}
