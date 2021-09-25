package top.auzero.autumn.exception;

public class BizException extends BaseException{


    public BizException(IResultCode responseEnum, Object[] args, String message) {
        super(responseEnum, args, message);
    }

    public BizException(IResultCode responseEnum, Object[] args, String message, Throwable cause) {
        super(responseEnum, args, message, cause);
    }
}
