package com.anvna.blog.dao;

import com.anvna.blog.po.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

/**
 * @InterfaceName UserRepository
 * @Description
 * @Author an_vna
 * @Date 2020/10/19 22:55
 * @Version V1.0
 **/
@Service
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * 查询数据库方法
     * @param username 用户名
     * @param password 密码
     * @return
     */
    User findByUsernameAndPassword(String username, String password);
}
