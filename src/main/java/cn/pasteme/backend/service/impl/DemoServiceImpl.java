package cn.pasteme.backend.service.impl;

import cn.pasteme.backend.service.DemoService;
import cn.pasteme.common.dto.TokenDTO;
import cn.pasteme.common.manager.DemoManager;
import cn.pasteme.common.vo.ContentVO;
import org.springframework.stereotype.Service;

/**
 * @Author: 白振宇
 * @Date： 2019/9/30 0:42
 */
@Service
public class DemoServiceImpl implements DemoService {

    private final DemoManager demoManager;

    public DemoServiceImpl(DemoManager demoManager) {
        this.demoManager = demoManager;
    }

    @Override
    public ContentVO getContentByKey(TokenDTO tokenDTO) {
        return demoManager.getContentByKey(tokenDTO);
    }
}
