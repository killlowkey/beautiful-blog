package com.predicated.blog.service;

import com.predicated.blog.entity.Comment;

import java.util.List;

/**
 * @author Ray
 * @date created in 2019/11/16 20:19
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    Comment saveComment(Comment comment);
}
