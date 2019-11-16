package com.predicated.blog.service.impl;

import com.predicated.blog.entity.User;
import com.predicated.blog.repository.UserRepository;
import com.predicated.blog.service.UserService;
import com.predicated.blog.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Ray
 * @date created in 2019/11/16 10:10
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User login(String username, String password) {
        // 对密码进行 md5 加密
        String md5Code = MD5Util.code(password);
        return userRepository.findByUsernameAndPassword(username, md5Code);
    }
}
