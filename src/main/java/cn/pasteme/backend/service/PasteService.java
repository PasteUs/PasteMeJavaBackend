package cn.pasteme.backend.service;

import cn.pasteme.common.dto.PasteRequestDTO;
import cn.pasteme.common.dto.PasteResponseDTO;
import cn.pasteme.common.utils.result.Response;
import org.springframework.stereotype.Service;

/**
 * @author Lucien
 * @date 2019/10/01 17:16
 * 暂时加一个注解，否则 Controller 的 Bean 会报错
 */
@Service
public interface PasteService {

    /**
     * 通过 key 和 password 得到 DTO
     * 需要判断 key 的类别
     * @param pasteRequestDTO key + password
     * @return DTO
     */
    Response<PasteResponseDTO> get(PasteRequestDTO pasteRequestDTO);

    /**
     * 删除 key 对应的 Paste
     * @param key key
     * @return 成功与否
     */
    Boolean delete(String key);

    /**
     * 创建一个由系统生成 key 的 record
     * @param pasteDTO DTO
     * @return key
     */
    String createPermanent(PasteRequestDTO pasteDTO);

    /**
     * 创建一个随机/自定义 key 的 Temporary record
     * 如果有 key 则创建自定义，无 key 则创建随机的
     * @param pasteDTO DTO
     * @return key
     */
    String createTemporary(PasteRequestDTO pasteDTO);
}
