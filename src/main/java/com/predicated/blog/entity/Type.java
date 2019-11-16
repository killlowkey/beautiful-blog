package com.predicated.blog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ray
 * @date created in 2019/11/16 9:13
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_type")
public class Type extends BaseEntity{
    private String name;

    @OneToMany(mappedBy = "type")
    private List<Blog> blogs = new ArrayList<>();
}
