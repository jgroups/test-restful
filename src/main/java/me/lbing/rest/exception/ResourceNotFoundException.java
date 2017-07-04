package me.lbing.rest.exception;

/**
 * 资源未找到异常
 *
 * @author jgroup
 * @date: 2017-06-29 0:34
 */
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 4880328265878141724L;
    public ResourceNotFoundException() {
        super();
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
