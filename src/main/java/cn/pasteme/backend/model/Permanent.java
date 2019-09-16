package cn.pasteme.backend.model;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class Permanent {
    private String key;
    private String lang;
    private String content;
    private String password;
    private String clientIp;
    private Timestamp createdAt;
    private Timestamp deletedAt;
}
