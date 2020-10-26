package com.anvna.blog.dao;

import com.anvna.blog.po.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
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

    /**
     * 更新 view
     * @param id
     * @return
     */
    @Transactional
    @Modifying
    @Query("update t_blog b set b.views = b.views + 1 where b.id = ?1")
    int updateViews(Long id);

    @Query("select function('date_format', b.updateTime, '%Y') as year " +
            "from t_blog b " +
            "group by function('date_format', b.updateTime, '%Y') " +
            "order by year desc ")
    List<String> findGroupYear();

    @Query("select b from t_blog b where function('date_format', b.updateTime, '%Y') =?1")
    List<Blog> findByYear(String yead);


}
