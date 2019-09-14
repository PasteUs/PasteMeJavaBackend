package cn.pasteme.backend.model;


import lombok.Data;
import org.w3c.dom.Text;

import java.net.InetAddress;
import java.sql.Time;

@Data
public class Permanent {
    private Integer key;
    private String lang;
    private Text content;
    private String password;
    private InetAddress client_ip;
    private Time created_at;
    private Time deleted_at;
}
