package com.sy.by.myservice;

import com.sy.by.bean.Section;
import com.sy.by.mapper.SectionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SectionService {

    @Autowired
    SectionMapper sectionMapper;

    //增加章节
    public void  insert(Section section)
    {
        sectionMapper.insertSction(section);
    }

    //通过章节id删除章节
    public void deleteByid(int section_id)
    {
        sectionMapper.delete(section_id);
    }

    //通过课程id 查看所有的 课程章节
    public ArrayList<Section> selectAll(int id)
    {
        return sectionMapper.selectBysubjectId(id);
    }

    public void update(Section section)
    {
        sectionMapper.update(section);
    }

}
