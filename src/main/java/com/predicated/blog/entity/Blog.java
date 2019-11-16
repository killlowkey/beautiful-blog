package com.predicated.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 博客实体类
 * @author Ray
 * @date created in 2019/11/16 9:03
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_blog")
public class Blog extends BaseEntity{

    /**
     * 博客标题
     */
    private String title;

    /**
     * 博客内容
     */

    private String content;

    /**
     * 首图，采用base64编码
     */
    private String firstPicture;

    /**
     * 博客标记：原创、转载、翻译
     */
    private String flag;

    /**
     * 浏览次数
     */
    private Integer views;

    /**
     * 赞赏功能
     */
    private boolean appreciation;

    /**
     * 转载声明
     */
    private boolean shareStatement;

    /**
     * 评论功能
     */
    private boolean commentTabled;

    /**
     * 是否发表：发表、保存
     */
    private boolean published;

    /**
     * 推荐
     */
    private boolean recommend;

    @ManyToOne
    private Type type;

    /**
     * {@code CascadeType.PERSIST} 级联新增，新增一个tag也会保存到数据库中
     */
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private List<Tag> tags = new ArrayList<>();

    @ManyToOne
    private User user;

    @OneToMany(mappedBy = "blog")
    private List<Comment> comments = new ArrayList<>();
}
