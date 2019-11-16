package com.predicated.blog.repository;

import com.predicated.blog.entity.Type;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * @author Ray
 * @date created in 2019/11/16 11:13
 */
public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByName(String name);
}
