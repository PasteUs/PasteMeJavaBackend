package cn.pasteme.backend.mapper;

import cn.pasteme.backend.model.*;

import lombok.Setter;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface myMapper {
    @Select("select * from `pasteme`.`permanents` where `key` = #{key}")
    Permanent getByKeyPermanent(Integer key);

    Temporary getByKeyTemporary(String key);

    @Select("select count(`pasteme`.`temporaries`.`key`) from `pasteme`.`temporaries` where `key` = #{key}")
    Integer isExit(String key);

    @Insert("insert into `pasteme`.`temporaries` (`key`, `lang`, `content`, `password`, `client_ip`, `created_at`) value (#{key}, #{lang}, #{content}, #{password}, #{clientIp}, #{createdAt})")
    void insert(Temporary temporary);

}
