package com.anvna.blog.service.impl;

import com.anvna.blog.NotFoundException;
import com.anvna.blog.dao.BlogRepository;
import com.anvna.blog.po.Blog;
import com.anvna.blog.po.Tag;
import com.anvna.blog.po.Type;
import com.anvna.blog.service.BlogService;
import com.anvna.blog.service.TagService;
import com.anvna.blog.util.MarkdownUtils;
import com.anvna.blog.util.MyBeanUtils;
import com.anvna.blog.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @ClassName BlogServiceImpl
 * @Description
 * @Author an_vna
 * @Date 2020/10/21 20:02
 * @Version V1.0
 **/
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private TagService tagService;

    @Override
    public Blog getBlog(Long id) {
        return blogRepository.getOne(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogRepository.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 组合动态查询条件
                List<Predicate> predicates = new ArrayList<>();
                // 判断文章标题非空
                if( !"".equals(blog.getTitle()) && blog.getTitle() != null  ){
                    predicates.add(criteriaBuilder.like(root.<String>get("title"), "%" + blog.getTitle() + "%"));
                }
                // 判断查询分类
                if( blog.getTypeId()!= null ){
                    predicates.add(criteriaBuilder.equal(root.<Type>get("type").get("id"), blog.getTypeId()));
                }

                if( blog.isRecommend() ){
                    predicates.add(criteriaBuilder.equal(root.<Boolean>get("ifRecommend"), blog.isRecommend()));
                }
                criteriaQuery.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        }, pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogRepository.findAll(pageable);
    }


    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if( blog.getId() == null ){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
        }else{
            blog.setUpdateTime(new Date());
        }
        return blogRepository.save(blog);
    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = getBlog(id);
        if( b == null ){
            throw new NotFoundException("该博客不存在!");
        }
        BeanUtils.copyProperties(blog, b, MyBeanUtils.getNullFieldsName(blog));
        b.setUpdateTime(new Date());
        return blogRepository.save(b);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogRepository.deleteById(id);
    }

    @Override
    public List<Blog> listRecommendBlog(Integer size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "updateTime");
        Pageable pageable = PageRequest.of(0, size, sort);
        return blogRepository.findTop(pageable);
    }

    @Override
    public Page<Blog> listBlog(String query, Pageable pageable) {
        return blogRepository.findByQuery(query, pageable);
    }

    @Transactional
    @Override
    public Blog getAndConvert(Long id) {
        Blog blog = blogRepository.getOne(id);
        if( blog == null ) {
            throw new NotFoundException("博客不存在!");
        }
        // 复制新对象防止操作数据库
        Blog b = new Blog();
        BeanUtils.copyProperties(blog, b);
        // 将 markdown 转换为 html
        String content = b.getContent();
        String s = MarkdownUtils.markdownToHtmlExtensions(content);
        blogRepository.updateViews(id);
        b.setContent(s);
        return b;
    }
}
