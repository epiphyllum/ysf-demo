package com.koolyun.ysf.ysfdemo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    private boolean success;
    private int code;
    private String msg;
    private T data;

    private static int okCode = 200;
    private static int failCode = 500;

    /**
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> Result<T> ok(T data) {
        return new Result<>(true, okCode, null, data);
    }

    /**
     *  ok 的 alias
     * @param data
     * @param <T>
     * @return
     */
    public static  <T> Result<T> success(T data) {
        return Result.ok(data);
    }

    /**
     *
     * @param code
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(int code,String msg){
        Result<T> result = new Result<>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }

    /**
     *
     * @param msg
     * @param <T>
     * @return
     */
    public static <T> Result<T> fail(String msg){
        return new Result<>(false, failCode, msg, null);
    }
}
