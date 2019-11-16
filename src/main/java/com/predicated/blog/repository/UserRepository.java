package com.predicated.blog.repository;

import com.predicated.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Ray
 * @date created in 2019/11/16 10:05
 */
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 用户登陆
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User findByUsernameAndPassword(String username, String password);
}
