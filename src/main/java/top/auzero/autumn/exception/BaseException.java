package top.auzero.autumn.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BaseException extends RuntimeException{

    private IResultCode responseEnum;

    private Object[] args;

    private String message;

    private Integer code;

    private Throwable cause;

    public BaseException(Integer code) {
        this(code, null);
    }

    public BaseException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public BaseException(IResultCode responseEnum, Object[] args, String message) {
        this(responseEnum, args, message, null);
    }

    public BaseException(IResultCode responseEnum, Object[] args, String message, Throwable cause) {
        this.responseEnum = responseEnum;
        this.args = args;
        this.message = message;
        this.cause = cause;
    }

}
