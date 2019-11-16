package com.predicated.blog.repository;

import com.predicated.blog.entity.Type;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * @author Ray
 * @date created in 2019/11/16 11:13
 */
public interface TypeRepository extends JpaRepository<Type, Long> {
    Optional<Type> findByName(String name);

    @Query("select t from Type t")
    List<Type> findTop(Pageable pageable);
}
