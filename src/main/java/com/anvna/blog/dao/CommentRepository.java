package com.anvna.blog.dao;

import com.anvna.blog.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @InterfaceName CommentRepository
 * @Description comment 数据操作
 * @Author an_vna
 * @Date 2020/10/25 20:17
 * @Version V1.0
 **/
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * 根据 id 查询 blog
     * @return
     */
    List<Comment> findByBlogIdAndParentNull(Long blogId, Sort sort);
}
