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
 * @author 白振宇
 * @date 2019/9/30 1:04
 */
@RestController
public class DemoController {

    private final DemoService demoService;
    private final PasteService pasteService;

    public DemoController(DemoService demoService, PasteService pasteService) {
        this.demoService = demoService;
        this.pasteService = pasteService;
    }

    @GetMapping("demo")
    @RequestLogging(withResponse = true)
    public Result<ContentVO> demo(TokenDTO tokenDTO) {
        if (tokenDTO == null || tokenDTO.getKey() == null) {
            return Result.error(CodeMessage.PARAM_ERROR);
        }
        Optional<ContentVO> contentVO = Optional.of(demoService.getContentByKey(tokenDTO));
        if (contentVO.filter(c -> c.getKey() == null).isPresent()) {
            return Result.error(CodeMessage.CONTENT_EMPTY);
        }
        return Result.success(contentVO.get());
    }

    @GetMapping({"/{key}", "/{key}/{pwd}"})
    public Result getPaste(@PathVariable String key,
                       @PathVariable(required = false) String pwd) {
        //TODO
        //获取Paste
        return Result.success(pasteService.getPasteByKey(key, pwd));
    }

    @PostMapping("/")
    public Result createPerm(@RequestBody PasteDO pasteDO) {
        //TODO
        //创建随机Key的永久Paste
        return null;
    }

    @PostMapping("/once")
    public Result createTemp(@RequestBody PasteDO pasteDO) {
        //TODO
        //创建随机Key的阅后即焚Paste
        return null;
    }

    @PutMapping("/{key}")
    public Result customTemp(@PathVariable String key,
                             @RequestBody PasteDO pasteDO) {
        //TODO
        //自定义Key创建阅后即焚Paste
        return null;
    }

}
