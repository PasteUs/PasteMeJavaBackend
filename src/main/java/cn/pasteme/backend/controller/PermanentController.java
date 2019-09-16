package cn.pasteme.backend.controller;

import org.jboss.logging.Cause;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import cn.pasteme.backend.model.*;
import cn.pasteme.backend.mapper.*;
import cn.pasteme.backend.util.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class PermanentController {
    private final myMapper mapper;

    private final Util util;

    public PermanentController(Util util, myMapper mapper) {
        this.util = util;
        this.mapper = mapper;
    }

    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    public Map<String, String> getContent(@PathVariable String token) {
        Map<String, String> map = new HashMap<>();

        // token 的情况分为两种：
        // 情况1：有密码，token 为key，password
        // 情况2：没有密码，token 为key

        String[] tmp = token.split(",");
        int len = tmp.length;
        String key = tmp[0];
        String password;

        // key 的情况分为两种：
        // 情况1：permanent（永久保存），这种类型的 paste 的 key 只包含数字
        // 情况2：temporary（临时保存），这种类型的 paste 的 key 要包含字母，且不区分大小写

        // 数据中的 password 进行了md5加密

        try {
            boolean isNum = key.matches("[0-9]+");
            if (isNum) {
                Permanent permanent;
                permanent = mapper.getByKeyPermanent(Integer.valueOf(key));
                if (len >= 2) {
                    password = util.getMD5Str(tmp[1]);
                    if (password.equals(permanent.getPassword())) {
                        map.put("status", "success");
                        map.put("lang", permanent.getLang());
                        map.put("content", permanent.getContent());
                    } else {
                        map.put("status", "401");
                        map.put("error", "password is wrong");
                    }
                } else {
                    map.put("status", "success");
                    map.put("lang", permanent.getLang());
                    map.put("content", permanent.getContent());
                }
            } else {
                Temporary temporary;
                temporary = mapper.getByKeyTemporary(key);
                if (len >= 2) {
                    password = util.getMD5Str(tmp[1]);
                    if (password.equals(temporary.getPassword())) {
                        map.put("status", "success");
                        map.put("lang", temporary.getLang());
                        map.put("content", temporary.getContent());
                    } else {
                        map.put("status", "401");
                        map.put("error", "password is wrong");
                    }
                } else {
                    map.put("status", "success");
                    map.put("lang", temporary.getLang());
                    map.put("content", temporary.getContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "401");
            map.put("error", "unauthorized");
            map.put("message", e.toString());
        }
        return map;
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.PUT)
    public Map<String, String> putTemporary(@PathVariable String key, @RequestBody Temporary temporary,
                                            HttpServletRequest httpServletRequest) {
        Map<String, String> map = new HashMap<>();
        try {
            if (mapper.isExit(key).equals(0)) {
                temporary.setKey(key);
                if(temporary.getPassword() != null && !temporary.getPassword().equals("")){
                    temporary.setPassword(util.getMD5Str(temporary.getPassword()));
                }
                String ip = util.GetIPAddress(httpServletRequest);
                temporary.setClientIp(ip);
                temporary.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
                mapper.insert(temporary);
                map.put("status","success");
                map.put("key",temporary.getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }
}
