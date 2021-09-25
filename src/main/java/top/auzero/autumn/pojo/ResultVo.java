package top.auzero.autumn.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import top.auzero.autumn.exception.IResultCode;

/**
*
* @author Auzero
* @since 2021/9/24
*/
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultVo<T> {

    private Integer code;
    private String msg;
    private T data;

    public ResultVo(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ResultVo<?> create(Integer code, String msg) {
        return new ResultVo<>(code, msg);
    }

    public static ResultVo<?> success() {
        return create(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg());
    }

    public static <T> ResultVo<T> data(T data) {
        return new ResultVo<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMsg()).setData(data);
    }

    public static ResultVo<?> failure() {
        return create(ResultCode.FAILURE.getCode(), ResultCode.FAILURE.getMsg());
    }

    public static ResultVo<?> failure(String msg) {
        return create(ResultCode.FAILURE.getCode(), msg);
    }

    public static ResultVo<?> error() {
        return create(ResultCode.ERROR.getCode(), ResultCode.ERROR.getMsg());
    }

    public static ResultVo<?> error(String msg) {
        return create(ResultCode.ERROR.getCode(), msg);
    }

    public static ResultVo<?> tokenExp() {
        return create(ResultCode.TOKE_ERROR.getCode(), ResultCode.TOKE_ERROR.getMsg());
    }

    public static ResultVo<?> failure(IResultCode code, String msg) {
        return new ResultVo<>(code, msg);
    }

    public ResultVo(IResultCode code, String msg) {
        this.code = code.getCode();
        this.msg = msg;
    }

    public ResultVo<T> setData(T data) {
        this.data = data;
        return this;
    }
}
