package com.sy.by.mapper;

import com.sy.by.bean.Topic;
import org.apache.ibatis.jdbc.SQL;
//更新topic表
public class UpdateTopic {
    public String updateTopic(final Topic topic)
    {
        return new SQL(){
            {
                UPDATE("topic");
                if(topic.getTopic_type()!=null){
                    SET("topic_type=#{topic_type}");
                }
                if(topic.getTopic_content()!=null)
                {
                    SET("topic_content=#{topic_content}");
                }
                if(topic.getA()!=null)
                {
                    SET("A=#{A}");
                }
                if(topic.getB()!=null)
                {
                    SET("B=#{B}");
                }
                if(topic.getC()!=null)
                {
                    SET("C=#{C}");
                }
                if(topic.getD()!=null)
                {
                    SET("D=#{D}");
                }
                if(topic.getAnswer()!=0)
                {
                    SET("answer=#{answer}");
                }
                if(topic.getQuestion_id()!=0)
                {
                    SET("question_id=#{question_id}");
                }
                if(topic.getSection_id()!=0)
                {
                    SET("section_id=#{section_id}");
                }
                WHERE("id=#{id}");
            }
        }.toString();
    }
}
