package top.auzero.autumn.exception;

import java.io.Serializable;

/**
*
* @author Auzero
* @since 2021/9/24
*/
public interface IResultCode extends Serializable {

    String getMsg();

    Integer getCode();

}
