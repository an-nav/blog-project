package com.anvna.blog.service;

import com.anvna.blog.po.Blog;
import com.anvna.blog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

/**
 * @InterfaceName BlogService
 * @Description
 * @Author an_vna
 * @Date 2020/10/21 20:00
 * @Version V1.0
 **/
@Service
public interface BlogService {
    /**
     * 查询 blog 方法
     * @param id
     * @return
     */
    Blog getBlog(Long id);

    /**
     * 分页查询 blog
     * @param pageable
     * @param blog
     * @return
     */
    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Pageable pageable);


    /**
     * 获取推荐博客列表
     * @param size
     * @return
     */
    List<Blog> listRecommendBlog(Integer size);

    /**
     * 新增 blog
     * @param blog
     * @return
     */
    Blog saveBlog(Blog blog);

    /**
     * 修改 blog
     * @param id
     * @param blog
     * @return
     */
    Blog updateBlog(Long id, Blog blog);


    /**
     * 删除 blog
     * @param id
     */
    void deleteBlog(Long id);

    Page<Blog> listBlog(String query, Pageable pageable);

    Blog getAndConvert(Long id);

    /**
     * 根据 TagId 查询
     * @param tagId
     * @param pageable
     * @return
     */
    Page<Blog> listBlog(Long tagId, Pageable pageable);

    /**
     * 回档 blog
     * @return
     */
    Map<String, List<Blog>> archiveBlog();

    /**
     * 获取 blog 条数
     * @return
     */
    Long countBlog();

}
