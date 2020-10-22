package com.anvna.blog.dao;

import com.anvna.blog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @InterfaceName BlogRepository
 * @Description
 * @Author an_vna
 * @Date 2020/10/21 20:03
 * @Version V1.0
 **/
public interface BlogRepository  extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {

    @Query("select b from t_blog b where b.ifRecommend = true")
    List<Blog> findTop(Pageable pageable);


    @Query("select b from t_blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);
}
