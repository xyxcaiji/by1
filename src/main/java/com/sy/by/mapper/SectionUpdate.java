package com.sy.by.mapper;

import com.sy.by.bean.Section;

import org.apache.ibatis.jdbc.SQL;


public class SectionUpdate {
    public String update(final Section section)
    {
        return new SQL(){
            {
                UPDATE("section");
                if(section.getSection_name()!=null){
                    SET("section_name=#{section_name}");
                }
                if(section.getSubject_id()!=0)
                {
                    SET("subject_id=#{subject_id}");
                }
                WHERE("section_id=#{section_id}");
            }
        }.toString();
    }
}
