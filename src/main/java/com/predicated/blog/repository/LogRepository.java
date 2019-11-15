package com.predicated.blog.repository;

import com.predicated.blog.entity.Log;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ray
 * @date created in 2019/11/15 22:05
 */
public interface LogRepository extends JpaRepository<Log, Long> {
}
