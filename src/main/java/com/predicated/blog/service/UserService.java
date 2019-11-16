package com.predicated.blog.service;

import com.predicated.blog.entity.User;

/**
 * @author Ray
 * @date created in 2019/11/16 10:09
 */
public interface UserService {
    User login(String username, String password);
}
