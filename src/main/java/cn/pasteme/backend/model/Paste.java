package cn.pasteme.backend.model;

import lombok.Data;

import java.sql.Timestamp;

/**
 * Created by Lucien on 2019/9/27.
 */
@Data
public abstract class Paste {

    // 主键
    private String key;

    // 高亮类型
    private String lang;

    // Paste 内容
    private String content;

    // 密码
    private String password;

    // 创建者 IP
    private String clientIp;

    // 创建日期
    private Timestamp createdAt;

    /**
     * 通过 key 读取 Paste 主体
     * @param key Paste key
     * @return Paste Object
     */
    public abstract Paste get(String key);

    /**
     * 将 Paste 持久化至数据库
     * @return Paste 的 key
     */
    public abstract String save();

    /**
     * 删除 key 对应的 Paste
     * @return 删除成功与否
     */
    public abstract Boolean delete(String key);

    /**
     * 判断 key 在持久层中是否存在
     * @param key 主键
     * @return Boolean
     */
    public abstract Boolean isExist(String key);

    /**
     * 判断 key 在持久层中是否存在且被删除
     * 主要针对 Permanent 起作用
     * @param key 主键
     * @return Boolean
     */
    public abstract Boolean isDeleted(String key);
}