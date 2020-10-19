package com.anvna.blog.service;

import com.anvna.blog.po.User;
import org.springframework.stereotype.Service;

/**
 * @InterfaceName UserService
 * @Description
 * @Author an_vna
 * @Date 2020/10/19 22:51
 * @Version V1.0
 **/
@Service
public interface UserService {
    /**
     * 检查用户名字密码
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User checkUser(String username, String password);
}
