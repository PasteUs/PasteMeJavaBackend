package cn.pasteme.backend.mapper;

import cn.pasteme.backend.model.Permanent;

import org.apache.ibatis.annotations.*;

@Mapper
public interface permanentMapper {
    @Select("select * from 'pasteme'.'permanents' where key = #{key} and password = #{password}")
    Permanent getByKey(int key,String password);
}
