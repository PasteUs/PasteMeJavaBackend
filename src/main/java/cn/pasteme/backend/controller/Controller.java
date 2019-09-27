package cn.pasteme.backend.controller;

import cn.pasteme.backend.model.Paste;
import cn.pasteme.backend.model.impl.Permanent;
import cn.pasteme.backend.model.impl.Temporary;
import cn.pasteme.backend.util.Util;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Lucien on 2019/9/27.
 */
@RestController
@RequestMapping(value = "/v2.0.0")
public class Controller {

    private final Util util;

    public Controller(Util util) {
        this.util = util;
    }

    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    public Map<String, String> getContent(@PathVariable String token) {
        Map<String, String> map = new HashMap<>();

        String[] tmp = token.split(",");
        String key = tmp[0];

        Paste paste;

        try {
            boolean isNum = key.matches("[0-9]+");
            paste = isNum ? new Permanent().get(key) : new Temporary().get(key);;

            // 用户是否有访问此 Paste 的权限
            boolean accessible = true;

            // 如果此 Paste 有密码，那么进行鉴权
            if (tmp.length > 1) {

                // 数据中的 password 进行了md5加密
                String password = util.getMD5Str(tmp[1]);
                accessible = password.equals(paste.getPassword());
            }

            if (accessible) {
                // TODO
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", "401");
            map.put("error", "unauthorized");
            map.put("message", e.toString());
        }
        return map;
    }

    // TODO POST

    // TODO PUT
}
