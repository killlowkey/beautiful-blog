package com.predicated.blog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 异常枚举
 *
 * @author Ray
 * @date created in 2019/11/15 21:00
 */
@Getter
@AllArgsConstructor
public enum CustomExceptionEnum {

    /**
     *
     */
    NOT_FOUND(404, "未找到");

    private Integer code;
    private String message;

}
