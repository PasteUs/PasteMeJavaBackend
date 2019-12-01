package cn.pasteme.backend.exception;

import cn.pasteme.common.utils.result.Response;
import cn.pasteme.common.utils.result.ResponseCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Moyu
 * @version 1.0.0
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public static Response exceptionHandler(Exception e) {
        if (e instanceof DuplicateException || e instanceof ParamErrorException) {
            return Response.error(ResponseCode.PARAM_ERROR);
        } else {
            return cn.pasteme.common.utils.exception.GlobalExceptionHandler.exceptionHandler(e);
        }
    }
}
