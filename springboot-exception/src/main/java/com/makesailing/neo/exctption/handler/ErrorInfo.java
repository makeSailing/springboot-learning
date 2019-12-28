package com.makesailing.neo.exctption.handler;

import lombok.Data;

/**
 * #
 *
 * @Author: jamie.li
 * @Date: Created in  2018/8/7 23:28
 */
@Data
public class ErrorInfo<T> {

    public static final Integer OK = 200;
    public static final Integer ERROR = 100;

    private Integer code;

    private String message;

    private String url;

    private T data;
}
