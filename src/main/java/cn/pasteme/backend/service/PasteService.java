package cn.pasteme.backend.service;


import cn.pasteme.common.dto.PasteDTO;
import cn.pasteme.common.utils.result.Result;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lucien
 * @date 2019/10/01 17:16
 */
@Service
public interface PasteService {

    /**
     * 通过 key 和 password 得到 DTO
     *
     * @param key      保证长度属于 [3, 8]，非数字 0 开头
     * @param password 密码
     * @return DTO
     */
    Result<PasteDTO> get(String key, String password);

    /**
     * 删除 key 对应的 Paste
     *
     * @param key key
     * @return 成功与否
     */
    Boolean delete(String key);

    /**
     * 创建一个由系统生成 key 的 record
     * path / 对应 Permanent，path /once 对应 Temporary
     *
     * @param pasteDTO           DTO
     * @param httpServletRequest Http Context
     * @return key
     */
    String create(PasteDTO pasteDTO, HttpServletRequest httpServletRequest);

    /**
     * 创建一个自定义 key 的 Temporary record
     *
     * @param key                从 path 中拿到的 key
     * @param pasteDTO           DTO
     * @param httpServletRequest Http Context
     * @return key
     */
    String create(String key, PasteDTO pasteDTO, HttpServletRequest httpServletRequest);
}
