package top.auzero.autumn.exception;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import top.auzero.autumn.pojo.ResultVo;

@Component
@RestControllerAdvice
@AllArgsConstructor
public class ResultExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResultVo<?> handleBaseException(BaseException e) {
        return ResultVo.failure(e.getResponseEnum(), e.getMessage());
    }

}
