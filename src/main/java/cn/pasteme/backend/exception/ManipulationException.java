package cn.pasteme.backend.exception;

import cn.pasteme.common.utils.result.ResponseCode;

/**
 * @author Moyu
 * @version 1.0.0
 */
public class ManipulationException extends RuntimeException {
    private ResponseCode responseCode;

    public ManipulationException(ResponseCode responseCode) {
        super(responseCode.toString());
        this.responseCode = responseCode;
    }

    public ManipulationException(String message) {
        super(message);
    }

    public ResponseCode getResponseCode() {
        return this.responseCode;
    }
}
