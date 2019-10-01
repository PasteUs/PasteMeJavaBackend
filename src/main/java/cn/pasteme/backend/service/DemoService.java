package cn.pasteme.backend.service;

import cn.pasteme.common.dto.TokenDTO;
import cn.pasteme.common.vo.ContentVO;

/**
 * @author 白振宇
 * @date 2019/9/30 00:41
 */
public interface DemoService {
    ContentVO getContentByKey(TokenDTO tokenDTO);
}
