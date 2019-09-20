package cn.pasteme.backend.mapper;

import cn.pasteme.backend.model.*;

import lombok.Setter;
import org.apache.ibatis.annotations.*;

@Mapper
public interface myMapper {
    @Select("select * from `pasteme`.`permanents` where `key` = #{key}")
    Permanent getByKeyPermanent(Integer key);

    @Select("select * from `pasteme`.`temporaries` where `key` = #{key}")
    Temporary getByKeyTemporary(String key);

    @Select("select count(`pasteme`.`temporaries`.`key`) from `pasteme`.`temporaries` where `key` = #{key}")
    Integer isExit(String key);

    @Insert("insert into `pasteme`.`temporaries` (`key`, `lang`, `content`, `password`, `client_ip`, `created_at`) value (#{key}, #{lang}, #{content}, #{password}, #{clientIp}, #{createdAt})")
    void insertTemporary(Temporary temporary);

    @Insert("insert into `pasteme`.`permanents` (`lang`, `content`, `password`, `client_ip`, `created_at`) value (#{lang}, #{content}, #{password}, #{clientIp}, #{createdAt})")
    @Options(useGeneratedKeys = true, keyProperty = "key", keyColumn = "key")
    void insertPermanent(Permanent permanent);

    @Delete("delete from `pasteme`.`temporaries` where `key`= #{key};")
    void deleteTemporary(String key);

}
