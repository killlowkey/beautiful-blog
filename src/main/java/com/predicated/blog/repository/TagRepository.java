package com.predicated.blog.repository;

import com.predicated.blog.entity.Tag;
import com.predicated.blog.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Ray
 * @date created in 2019/11/16 11:13
 */
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String name);
}
