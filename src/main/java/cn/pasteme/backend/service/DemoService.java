package cn.pasteme.backend.service;

import cn.pasteme.common.dto.TokenDTO;
import cn.pasteme.common.vo.ContentVO;

/**
 * @Author: 白振宇
 * @Date： 2019/9/30 0:41
 */
public interface DemoService {
    ContentVO getContentByKey(TokenDTO tokenDTO);
}
