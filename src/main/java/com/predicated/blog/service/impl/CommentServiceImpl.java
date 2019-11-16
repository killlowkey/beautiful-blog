package com.predicated.blog.service.impl;

import com.predicated.blog.entity.Comment;
import com.predicated.blog.repository.CommentRepository;
import com.predicated.blog.service.CommentService;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Ray
 * @date created in 2019/11/16 20:19
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        return commentRepository.findByBlogIdAndParentCommentNull(blogId, sort);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1) {
            comment.setParentComment(commentRepository.getOne(parentCommentId));
        } else {
            comment.setParentComment(null);
        }

        return commentRepository.save(comment);
    }
}
