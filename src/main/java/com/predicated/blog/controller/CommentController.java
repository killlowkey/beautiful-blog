package com.predicated.blog.controller;

import com.predicated.blog.entity.Comment;
import com.predicated.blog.entity.User;
import com.predicated.blog.service.BlogService;
import com.predicated.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * @author Ray
 * @date created in 2019/11/16 20:15
 */
@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private BlogService blogService;

    /**
     * 获取该页面所有评论
     *
     * @param blogId 博客id
     * @param model  数据模型
     */
    @GetMapping("/comment/{blogId}")
    public String comment(@PathVariable Long blogId, Model model) {
        model.addAttribute("comments", commentService.listCommentByBlogId(blogId));
        return "blog :: commentList";
    }

    /**
     * 提交评论
     *
     * @param comment 用户评论
     * @param session 用户session
     */
    @PostMapping("/comments")
    public String post(Comment comment, HttpSession session) {

        User user = (User) session.getAttribute("user");
        if (user != null) {
            // 判断是否是博主本人
            if (user.getType() == 1) {
                comment.setAdminComment(true);
            }
        } else {
            // 提交过评论用户保存到 session
            User commentUser = new User();
            commentUser.setNickname(comment.getNickname());
            commentUser.setType(0);
            commentUser.setEmail(comment.getEmail());
            session.setAttribute("user", commentUser);
        }

        Long blogId = comment.getBlog().getId();
        comment.setBlog(blogService.getBlog(blogId));
        commentService.saveComment(comment);

        return "redirect:/comment/" + blogId;
    }
}
