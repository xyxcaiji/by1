package com.sy.by.mapper;
/**
 *     private int section_id;
 *     private String section_name;
 *     private int subject_id;
 *     private String subject_name;
 */

import com.sy.by.bean.Section;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;

@Mapper
public interface SectionMapper {
    //通过课程查找
    @Select("select * from section where subject_id=#{id}")
    ArrayList<Section> selectBysubjectId(int id);

    @Options(useGeneratedKeys = true,keyProperty = "section_id")
    @Insert("insert into section(section_name,subject_id,subject_name) values(#{section_name},#{subject_id},#{subject_name})")
    void insertSction(Section section);

    @Delete("delete from section where section_id=#{section_id}")
    void delete(int section_id);


    @UpdateProvider(type = SectionUpdate.class,method = "update")
    void update(Section section);

}
