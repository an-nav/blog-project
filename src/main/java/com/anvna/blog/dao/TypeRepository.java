package com.anvna.blog.dao;

import com.anvna.blog.po.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @InterfaceName TypeRepository
 * @Description
 * @Author an_vna
 * @Date 2020/10/20 21:33
 * @Version V1.0
 **/
public interface TypeRepository extends JpaRepository<Type, Long> {
    // 通过分类名查询分类
    Type findByName(String name);
}
