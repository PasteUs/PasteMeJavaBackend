package cn.pasteme.backend.mapper;

import cn.pasteme.backend.model.Temporary;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface temporaryMapper {
    @Select("select * form 'pasteme'.'temporaries' where key = #{key} and password = #{password}")
    Temporary getByKey(String key,String password);
}
