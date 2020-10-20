package com.anvna.blog.service.impl;

import com.anvna.blog.dao.UserRepository;
import com.anvna.blog.po.User;
import com.anvna.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserServiceImp
 * @Description
 * @Author an_vna
 * @Date 2020/10/19 22:52
 * @Version V1.0
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {

        User user = userRepository.findByUsernameAndPassword(username, password);
        return user;
    }
}
