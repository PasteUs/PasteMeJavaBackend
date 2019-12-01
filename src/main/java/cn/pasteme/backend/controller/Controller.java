package cn.pasteme.backend.controller;

import cn.pasteme.backend.exception.DuplicateException;
import cn.pasteme.backend.exception.ManipulationException;
import cn.pasteme.backend.exception.ParamErrorException;
import cn.pasteme.backend.service.PasteService;
import cn.pasteme.common.annotation.ErrorLogging;
import cn.pasteme.common.annotation.RequestLogging;
import cn.pasteme.common.dto.PasteRequestDTO;
import cn.pasteme.common.dto.PasteResponseDTO;
import cn.pasteme.common.utils.Checker;
import cn.pasteme.common.utils.Util;
import cn.pasteme.common.utils.exception.GlobalException;
import cn.pasteme.common.utils.result.Response;
import cn.pasteme.common.utils.result.ResponseCode;

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
 * @author Moyu, Lucien
 * @version 1.0.3
 */
@Slf4j
@RestController
@RequestMapping(value = "/1.0.3")
public class Controller {

    private final PasteService pasteService;

    private final Checker checker;

    private final Util util;

    public Controller(PasteService pasteService, Checker checker, Util util) {
        this.pasteService = pasteService;
        this.checker = checker;
        this.util = util;
    }

    @RequestMapping(value = "/{token}", method = RequestMethod.GET)
    @RequestLogging(withResponse = true)
    @ErrorLogging
    public Response<PasteResponseDTO> get(@PathVariable String token) {
        if (token == null || token.isBlank()) {
            return Response.error(ResponseCode.PARAM_ERROR);
        }
        String key = util.token2Key(token);
        if (!checker.isValid(key)) {
            throw new ParamErrorException(ResponseCode.PARAM_ERROR);
        }
        String password = util.token2Password(token);
        if (password == null) {
            return Response.error(ResponseCode.PARAM_ERROR);
        }
        PasteRequestDTO pasteRequestDTO = new PasteRequestDTO(key, password);
        return pasteService.get(pasteRequestDTO);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    @RequestLogging(withResponse = true)
    @ErrorLogging
    public Response<String> createPermanent(@Valid @RequestBody PasteRequestDTO pasteRequestDTO,
                                          HttpServletRequest httpServletRequest) throws ManipulationException {
        pasteRequestDTO.setClientIp(httpServletRequest.getRemoteHost());
        return Response.success(pasteService.createPermanent(pasteRequestDTO));
    }

    @RequestMapping(value = "/once", method = RequestMethod.POST)
    @RequestLogging(withResponse = true)
    @ErrorLogging
    public Response<String> createTemporary(@Valid @RequestBody PasteRequestDTO pasteRequestDTO,
                                            HttpServletRequest httpServletRequest) throws ManipulationException {
        pasteRequestDTO.setClientIp(httpServletRequest.getRemoteHost());
        return Response.success(pasteService.createTemporary(pasteRequestDTO));
    }

    @RequestMapping(value = "/{key}", method = RequestMethod.PUT)
    @RequestLogging(withResponse = true)
    @ErrorLogging
    public Response<String> putTemporary(@Valid @PathVariable String key, @Valid @RequestBody PasteRequestDTO pasteRequestDTO,
                                       HttpServletRequest httpServletRequest) throws DuplicateException {
        pasteRequestDTO.setClientIp(httpServletRequest.getRemoteHost());
        pasteRequestDTO.setKey(key);
        return checker.isValid(key) ? Response.success(pasteService.createTemporary(pasteRequestDTO))
                                    : Response.error(ResponseCode.PARAM_ERROR);
    }
}
