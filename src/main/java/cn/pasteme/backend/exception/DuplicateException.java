package cn.pasteme.backend.exception;

import cn.pasteme.common.utils.result.ResponseCode;

/**
 * @author Moyu
 * @version 1.0.0
 */
public class DuplicateException extends PasteException {
    private ResponseCode responseCode;

    public DuplicateException(ResponseCode responseCode) {
        super(responseCode.toString());
        this.responseCode = responseCode;
    }

    public DuplicateException(String message) {
        super(message);
    }

    public ResponseCode getResponseCode() {
        return this.responseCode;
    }
}
