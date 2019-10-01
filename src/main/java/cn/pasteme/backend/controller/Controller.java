package cn.pasteme.backend.controller;

import cn.pasteme.backend.service.PasteService;
import cn.pasteme.common.dto.PasteDTO;
import cn.pasteme.common.utils.Checker;
import cn.pasteme.common.utils.Util;
import cn.pasteme.common.utils.result.Result;
import cn.pasteme.common.utils.result.CodeMessage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @author Lucien
 * @date 2019/10/01 17:16
 */
@Slf4j
@RestController
@RequestMapping(value = "/1.0.0")
public class Controller {

    private PasteService pasteService;

    private Checker checker;

    private Util util;

    public Controller(PasteService pasteService, Checker checker, Util util) {
        this.pasteService = pasteService;
        this.checker = checker;
        this.util = util;
    }

    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    public Result<PasteDTO> get(@NotBlank @PathVariable String token) {
        try {
            @NotBlank String key = util.token2Key(token);
            if (checker.isValid(key)) {
                @NotNull String password = util.token2Password(token);
                return pasteService.get(key, password);
            }
            return Result.error(CodeMessage.PARAM_ERROR);
        } catch (Exception e) {
            log.error("token = {}, exception = ", token, e);
            return Result.error(CodeMessage.SERVER_ERROR);
        }
    }

    @RequestMapping(value = {"/", "/once"}, method = RequestMethod.POST)
    public Result<String> createPermanent(@Valid @RequestBody PasteDTO pasteDTO,
                                          HttpServletRequest httpServletRequest) {
        try {
            return Result.success(pasteService.create(pasteDTO, httpServletRequest));
        } catch (Exception e) {
            log.error("pasteDTO = {}, exception = ", pasteDTO, e);
            return Result.error(CodeMessage.SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.PUT)
    public Result<String> putTemporary(@Valid @PathVariable String key, @Valid @RequestBody PasteDTO pasteDTO,
                                       HttpServletRequest httpServletRequest) {
        try {
            return checker.isValid(key) ? Result.success(pasteService.create(key, pasteDTO, httpServletRequest))
                                        : Result.error(CodeMessage.PARAM_ERROR);
        } catch (Exception e) {
            log.error("pasteDTO = {}, exception = ", pasteDTO, e);
            return Result.error(CodeMessage.SERVER_ERROR);
        }
    }
}
