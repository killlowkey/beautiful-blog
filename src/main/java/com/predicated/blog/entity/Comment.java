package com.predicated.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ray
 * @date created in 2019/11/16 9:16
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "t_comment")
public class Comment extends BaseEntity{

    /**
     * 昵称
     */
    private String nickname;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 内容
     */
    private String content;

    /**
     * 头像
     */
    private String avatar = "/images/elliot.jpg";

    /**
     * 对应的blog，多个评论对应一个blog
     */
    @ManyToOne
    private Blog blog;

    /**
     * 子评论
     */
    @OneToMany(mappedBy = "parentComment")
    private List<Comment> replyComments = new ArrayList<>();

    /**
     * 评论父节点
     */
    @ManyToOne
    private Comment parentComment;

    private boolean adminComment;
}
