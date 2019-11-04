package cn.pasteme.backend.util;

/**
 * @author moyu
 * @version 1.0.0
 */
public class ResponseCode extends cn.pasteme.common.utils.result.ResponseCode {

    public static cn.pasteme.common.utils.result.ResponseCode LONG_CONTENT = new ResponseCode(500205, "内容过长");

    private ResponseCode(int code, String message) {
        super(code, message);
    }
}
