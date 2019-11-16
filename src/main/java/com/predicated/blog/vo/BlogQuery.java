package com.predicated.blog.vo;

import lombok.Data;

/**
 * @author Ray
 * @date created in 2019/11/16 14:17
 */
@Data
public class BlogQuery {
    private String title;
    private Long typeId;
    private boolean recommend;
}
