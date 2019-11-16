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
    NOT_FOUND_ENTITY(100, "实体未找到"),
    ENTITY_EXIST(101, "实体类存在"),
    BLOG_NOT_EXIST(102, "博客不存在");

    private Integer code;
    private String message;

}
