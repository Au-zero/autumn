package top.auzero.autumn.pojo;

import top.auzero.autumn.exception.BizExceptionAssert;

/**
*
* @author Auzero
* @since 2021/9/24
*/
public enum ResultCode implements BizExceptionAssert {

    SUCCESS(200, "SUCCESS"),
    FAILURE(400, "FAILURE"),
    TOKE_ERROR(401, "UNAUTHORIZED"),
    ERROR(500, "SERVER ERROR")
    ;

    private final Integer code;

    private final String msg;

    ResultCode(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

}
