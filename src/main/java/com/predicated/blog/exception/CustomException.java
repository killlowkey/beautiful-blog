package com.predicated.blog.exception;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 自定义异常
 *
 * @author Ray
 * @date created in 2019/11/15 20:46
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
@NoArgsConstructor
public class CustomException extends RuntimeException{

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
