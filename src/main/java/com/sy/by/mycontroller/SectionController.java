package com.sy.by.mycontroller;

import com.sy.by.bean.Section;
import com.sy.by.myservice.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Scanner;

@Controller
public class SectionController {
    @Autowired
    SectionService sectionService;

    //增加
    @ResponseBody
    @GetMapping("/insertSection")
    public String insertSection(Section section)
    {
        boolean eq=true;
        //先查询某课程下的所有章节
        ArrayList<Section> sections = null;
        try {
            sections = sectionService.selectAll(section.getSubject_id());
            for(Section s1:sections)
            {
                if(section.getSection_name().equals(s1.getSubject_name()))
                {
                    eq=false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //增加成功
        if(eq)
        {
            sectionService.insert(section);
            return "1";
        }
        //增加失败
        return "0";
    }
    //查询某个课程对应的所有章节
    @ResponseBody
    @GetMapping("/selectAllBySubjectId")
    public ArrayList<Section> selectAllSectionBySubject(int id)
    {
        ArrayList<Section> sections = sectionService.selectAll(id);
        return sections;
    }
    //删除章节
    @ResponseBody
    @GetMapping("/deleteSectionByid")
    public  String deleteBySectionId(int section_id,int id)
    {
        boolean eq=false;
        //先查询某课程下的所有章节
        ArrayList<Section> sections = null;
        try {
            sections = sectionService.selectAll(id);
            for(Section s1:sections)
            {
                if(s1.getSection_id()==section_id)
                {
                    eq=true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //有这个id 可以进行删除
        if(eq)
        {
            sectionService.deleteByid(section_id);
            return "1";
        }
        //删除失败
        return "0";
    }
    //更新Section
    @ResponseBody
    @GetMapping("/updateSection")
    public String updateSection(Section section)
    {
        boolean eq=true;
        //先查询某课程下的所有章节
        ArrayList<Section> sections = null;
        try {
            sections = sectionService.selectAll(section.getSubject_id());
            for(Section s1:sections)
            {
                if(section.getSection_name().equals(s1.getSection_name()))
                {
                    eq=false;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //更新成功
        if(eq)
        {
            sectionService.update(section);
            return "1";
        }
        //更新失败
        return "0";

    }
}
