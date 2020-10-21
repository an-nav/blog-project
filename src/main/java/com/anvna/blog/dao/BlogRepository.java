package com.anvna.blog.dao;

import com.anvna.blog.po.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @InterfaceName BlogRepository
 * @Description
 * @Author an_vna
 * @Date 2020/10/21 20:03
 * @Version V1.0
 **/
public interface BlogRepository  extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
}
