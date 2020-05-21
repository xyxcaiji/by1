package com.sy.by.mapper;

import com.sy.by.bean.Collect;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.Arrays;

@Mapper
public interface CollectMapper {
    //增加 收藏
    @Options(useGeneratedKeys = true,keyProperty = "collect_id")
    @Insert("insert into collect(topic_id,user_id) values(#{topic_id},#{user_id})")
    void insertCollect(Collect collect);

    //查看所有收藏
    @Select("select * from collect where user_id=#{user_id}")
    ArrayList<Collect> selectCollectByUser(int user_id);
    //删除收藏
    @Delete("delete from collect where collect_id=#{collect_id} and user_id=#{user_id}")
    void deleteCollect(Collect collect);
}
