package cn.pasteme.backend.exception;

/**
 * @author Moyu
 * @version 1.0.0
 */
class PasteException extends RuntimeException {
    static final long serialVersionUID = -7034897190745766939L;

    PasteException(String message) {
        super(message);
    }
}
