package cn.pasteme.backend.controller;

import cn.pasteme.backend.service.DemoService;
import cn.pasteme.common.dto.TokenDTO;
import cn.pasteme.common.utils.Md5Util;
import cn.pasteme.common.utils.result.CodeMsg;
import cn.pasteme.common.utils.result.Result;
import cn.pasteme.common.vo.ContentVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @Author: 白振宇
 * @Date： 2019/9/30 1:04
 */
@RestController
public class DemoController {

    private final DemoService demoService;

    public DemoController(DemoService demoService) {
        this.demoService = demoService;
    }

    @GetMapping("demo")
    public Result<ContentVO> demo(TokenDTO tokenDTO) {
        if(tokenDTO==null || tokenDTO.getKey()==null) {
            return Result.error(CodeMsg.PARAM_ERROR);
        }
        Optional<ContentVO> contentVO = Optional.of(demoService.getContentByKey(tokenDTO));
        if(contentVO.filter(c -> c.getKey()==null).isPresent()) {
            return Result.error(CodeMsg.CONTENT_EMPTY);
        }
        return Result.success(contentVO.get());
    }

}
