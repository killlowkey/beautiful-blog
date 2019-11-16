package com.predicated.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * 标签实体类
 * @author Ray
 * @date created in 2019/11/16 9:15
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_tag")
public class Tag extends BaseEntity{
    private String name;

    @ManyToMany(mappedBy = "tags")
    private List<Blog> blogs = new ArrayList<>();
}
